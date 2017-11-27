require "/scripts/util.lua"
require "/scripts/vec2.lua"
require "/scripts/poly.lua"
require "/scripts/interp.lua"
require "/items/active/weapons/weapon.lua"
require "/items/active/weapons/melee/meleeslash.lua"

function init()
  animator.setGlobalTag("paletteSwaps", item.instanceValue("paletteSwaps", ""))
  animator.setGlobalTag("directives", "")
  animator.setGlobalTag("bladeDirectives", "")

  self.weapon = Weapon:new()

  self.weapon:addTransformationGroup("weapon", {0,0}, util.toRadians(item.instanceValue("baseWeaponRotation", 0)))
  self.weapon:addTransformationGroup("swoosh", {0,0}, math.pi/2)

  local primaryAttack = HammerSmash:new(item.instanceValue("primaryAttack"), item.instanceValue("stances"))
  self.weapon:addAbility(primaryAttack)
  
  local secondaryAttack = getAltAbility()
  if secondaryAttack then
    self.weapon:addAbility(secondaryAttack)
  end

  self.weapon:init()
end

function update(dt, fireMode, shiftHeld)
  self.weapon:update(dt, fireMode, shiftHeld)
end

function uninit()
  self.weapon:uninit()
end

-- Hammer primary attack
-- Extends default melee attack and overrides windup and fire
HammerSmash = MeleeSlash:new()

function HammerSmash:windup(windupProgress)
  self.weapon:setStance(self.stances.windup)

  local windupProgress = windupProgress or 0
  while self.fireMode == "primary" and (self.allowHold ~= false or windupProgress < 1) do
    windupProgress = math.min(1, windupProgress + (self.dt / self.stances.windup.duration))
    self.weapon.relativeWeaponRotation, self.weapon.relativeArmRotation = self:windupAngle(windupProgress)
    coroutine.yield()
  end

  if windupProgress >= (self.stances.windup.minWindup / self.stances.windup.duration) then
    self:setState(self.preslash)
  else
    self:setState(self.winddown, windupProgress)
  end
end

function HammerSmash:winddown(windupProgress)
  self.weapon:setStance(self.stances.windup)

  while windupProgress > 0 do
    if self.fireMode == "primary" then
      self:setState(self.windup, windupProgress)
      return true
    end
    
    windupProgress = math.max(0, windupProgress - (self.dt / self.stances.windup.duration))
    self.weapon.relativeWeaponRotation, self.weapon.relativeArmRotation = self:windupAngle(windupProgress)
    coroutine.yield()
  end
end

function HammerSmash:fire()
  self.weapon:setStance(self.stances.fire)
  self.weapon:updateAim()

  animator.setAnimationState("swoosh", "fire")
  animator.playSound("fire")
  animator.burstParticleEmitter(self.weapon.elementalType .. "swoosh")

  local smashMomentum = self.smashMomentum
  smashMomentum[1] = smashMomentum[1] * mcontroller.facingDirection()
  mcontroller.addMomentum(smashMomentum)

  local smashTimer = self.stances.fire.smashTimer
  local duration = self.stances.fire.duration
  while smashTimer > 0 or duration > 0 do
    smashTimer = math.max(0, smashTimer - self.dt)
    duration = math.max(0, duration - self.dt)

    local damageArea = partDamageArea("swoosh")
    if not damageArea and smashTimer > 0 then
      damageArea = partDamageArea("blade")
    end
    self.weapon:setDamage(self.damageConfig, damageArea, self.fireTime)

    if smashTimer > 0 then
      local groundImpact = world.polyCollision(poly.translate(poly.handPosition(animator.partPoly("blade", "groundImpactPoly")), mcontroller.position()))
      if mcontroller.onGround() or groundImpact then
        smashTimer = 0
        if groundImpact then
          animator.burstParticleEmitter("groundImpact")
          animator.playSound("groundImpact")
        end
      end
    end
    coroutine.yield()
  end

  self.cooldownTimer = self:cooldownTime()
end

function HammerSmash:windupAngle(ratio)
  local weaponRotation = interp.ranges(ratio, {
    {0.7, interp.linear, util.toRadians(self.stances.windup.idleHammerAngle), self.stances.windup.windupHammerAngle},
    {0.85, interp.linear, self.stances.windup.windupHammerAngle, self.stances.windup.dropHammerAngle},
    {0.925, interp.sin, self.stances.windup.dropHammerAngle, self.stances.windup.bounceHammerAngle},
    {1.0, interp.reverse(interp.sin), self.stances.windup.dropHammerAngle, self.stances.windup.bounceHammerAngle}
  })
  local armRotation = interp.sin(ratio, self.stances.windup.windupBaseAngle, self.stances.windup.windupBaseAngle + self.stances.windup.windupAngleRange)

  return util.toRadians(weaponRotation), util.toRadians(armRotation)
end