require "/scripts/util.lua"
require "/scripts/rect.lua"
require "/scripts/pathutil.lua"
require "/items/active/weapons/weapon.lua"

function setupAltAbility(altAbilityConfig)
  local blinkSlash = WeaponAbility:new(altAbilityConfig, altAbilityConfig.stances)

  function blinkSlash:init()
    self:reset()

    self.cooldownTimer = 0
  end

  function blinkSlash:update(dt, fireMode, shiftHeld)
    WeaponAbility.update(self, dt, fireMode, shiftHeld)

    self.cooldownTimer = math.max(0, self.cooldownTimer - dt)

    if self.weapon.currentAbility == nil 
       and self.fireMode == "alt"
       and mcontroller.onGround()
       and self.cooldownTimer == 0
       and status.overConsumeResource("energy", self.energyUsage) then

      self:setState(self.windup)
    end
  end

  function blinkSlash:windup()
    self.weapon:setStance(self.stances.windup)
    self.weapon:updateAim()

    util.wait(self.stances.windup.duration)

    self.blinkPosition = self:findBlinkPosition()
    if self.blinkPosition then
      self:setState(self.slash)
    else
      self.cooldownTimer = self.cooldownTime
    end
  end

  function blinkSlash:slash()
    local suppressMove = function()
      mcontroller.controlModifiers({movementSuppressed = true})
      mcontroller.controlParameters({
        gravityEnabled = false
      })
      mcontroller.setVelocity({0,0})
    end
    
    local slash = coroutine.create(self.slashAction)
    coroutine.resume(slash, self)

    while util.parallel(suppressMove, slash) do
      coroutine.yield()
    end
  end

  function blinkSlash:slashAction()
    status.addEphemeralEffect("blink")
    util.wait(0.25)

    local fromPosition = mcontroller.position()
    mcontroller.setPosition(self.blinkPosition)
    self.weapon.aimDirection = -self.weapon.aimDirection
    self.weapon:setStance(self.stances.slash)
    self.weapon:updateAim()

    util.wait(0.1)

    animator.setAnimationState("blinkSwoosh", "fire")
    animator.playSound("fire")

    util.wait(self.stances.slash.duration, function()
      local damageArea = partDamageArea("blinkSwoosh")
      self.weapon:setDamage(self.damageConfig, damageArea)
    end)

    status.removeEphemeralEffect("blink")
    status.addEphemeralEffect("blink")
    util.wait(0.25)
    mcontroller.setPosition(fromPosition)
    self.weapon.aimDirection = -self.weapon.aimDirection

    self.cooldownTimer = self.cooldownTime
  end

  function blinkSlash:reset()
    animator.setGlobalTag("directives", "")
  end

  function blinkSlash:uninit()
    self:reset()
  end

  function blinkSlash:findBlinkPosition()
    local searchPosition = vec2.add(mcontroller.position(), {self.blinkDistance * mcontroller.facingDirection(), 0})
    local groundPosition = findGroundPosition(searchPosition, -self.blinkYTolerance, self.blinkYTolerance, false, {"Null", "Block", "Dynamic", "Platform"})
    if groundPosition and (not self.requireLineOfSight or not world.lineTileCollision(mcontroller.position(), groundPosition, {"Null", "Block", "Dynamic"})) then
      return groundPosition
    end
  end

  return blinkSlash
end
