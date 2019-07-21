require "/scripts/util.lua"
require "/scripts/rect.lua"
require "/items/active/weapons/weapon.lua"

function setupAltAbility(altAbilityConfig)
  local elementalPillar = WeaponAbility:new(altAbilityConfig, altAbilityConfig.stances)

  function elementalPillar:init()
  end

  function elementalPillar:update(dt, fireMode, shiftHeld)
    WeaponAbility.update(self, dt, fireMode, shiftHeld)

    if self.weapon.currentAbility == nil and self.fireMode == "alt" and mcontroller.onGround() and not status.resourceLocked("energy") then
      self:setState(self.windup)
    end
  end

  -- Attack state: windup
  function elementalPillar:windup()
    self.weapon:setStance(self.stances.windup)
    self.weapon:updateAim()

    animator.setParticleEmitterActive(self.weapon.elementalType.."Charge", true)
    animator.playSound(self.weapon.elementalType.."charge")

    local wasFull = false
    local chargeTimer = 0
    while self.fireMode == "alt" and (chargeTimer == self.chargeTime or status.overConsumeResource("energy", (self.energyUsage / self.chargeTime) * self.dt)) do
      chargeTimer = math.min(self.chargeTime, chargeTimer + self.dt)

      if chargeTimer == self.chargeTime and not wasFull then
        wasFull = true
        animator.stopAllSounds(self.weapon.elementalType.."charge")
        animator.playSound(self.weapon.elementalType.."full", -1)
      end

      local chargeRatio = math.sin(chargeTimer / self.chargeTime * 1.57)
      self.weapon.relativeArmRotation = util.toRadians(util.lerp(chargeRatio, {self.stances.windup.armRotation, self.stances.windup.endArmRotation}))
      self.weapon.relativeWeaponRotation = util.toRadians(util.lerp(chargeRatio, {self.stances.windup.weaponRotation, self.stances.windup.endWeaponRotation}))

      mcontroller.controlModifiers({
        jumpingSuppressed = true,
        runningSuppressed = true
      })

      coroutine.yield()
    end

    animator.stopAllSounds(self.weapon.elementalType.."charge")
    animator.stopAllSounds(self.weapon.elementalType.."full")

    if chargeTimer > self.minChargeTime then
      self:setState(self.fire, chargeTimer / self.chargeTime)
    end
  end

  -- Attack state: fire
  function elementalPillar:fire(charge)
    self.weapon:setStance(self.stances.fire)

    self:fireElementalPillar(charge)
    animator.playSound("fire")

    util.wait(self.stances.fire.duration)
  end

  function elementalPillar:reset()
    animator.setParticleEmitterActive(self.weapon.elementalType.."Charge", false)
    animator.stopAllSounds(self.weapon.elementalType.."charge")
    animator.stopAllSounds(self.weapon.elementalType.."full")
  end

  function elementalPillar:uninit()
    self:reset()
  end

  -- Helper functions
  function elementalPillar:fireElementalPillar(charge)
    local impactPosition = self:impactPosition()

    if impactPosition then
      local projectileParameters = copy(self.projectileParameters)
      projectileParameters.powerMultiplier = activeItem.ownerPowerMultiplier()
      projectileParameters.power = projectileParameters.power * item.instanceValue("damageLevelMultiplier")
      projectileParameters.actionOnTimeout = {
        {
          action = "projectile",
          inheritDamageFactor = 1,
          type = self.projectileType,
          config = { }
        }
      }
      local projectileCount = math.floor(charge * self.pillarMaxHeight)
      animator.playSound(self.weapon.elementalType.."impact")
      local dir = mcontroller.facingDirection()
      for i = 0, (projectileCount - 1) do
        projectileParameters.timeToLive = i * 0.02
        projectileParameters.actionOnTimeout[1].config.timeToLive = self.pillarDuration - (2 * projectileParameters.timeToLive)
        local position = vec2.add(impactPosition, {0, i})
        if not world.pointTileCollision(position, {"Null", "Block", "Dynamic"}) then
          world.spawnProjectile("pillarspawner", position, activeItem.ownerEntityId(), {dir, 0}, false, projectileParameters)
        else
          return
        end
      end
    end
  end

  function elementalPillar:impactPosition()
    local dir = mcontroller.facingDirection()
    local startLine = vec2.add(mcontroller.position(), {dir * self.pillarBaseDistance, self.pillarVerticalTolerance[1]})
    local endLine = vec2.add(mcontroller.position(), {dir * self.pillarBaseDistance, self.pillarVerticalTolerance[2]})
    
    local blocks = world.collisionBlocksAlongLine(startLine, endLine, {"Null", "Block", "Dynamic"})
    if #blocks > 0 then
      return vec2.add(blocks[#blocks], {0.5, 1.5})
    end
  end

  return elementalPillar
end
