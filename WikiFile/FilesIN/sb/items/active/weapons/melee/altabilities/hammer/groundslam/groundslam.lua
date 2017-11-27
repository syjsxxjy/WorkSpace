require "/scripts/util.lua"
require "/scripts/interp.lua"
require "/items/active/weapons/weapon.lua"

function setupAltAbility(altAbilityConfig)
  local groundSlam = WeaponAbility:new(altAbilityConfig, altAbilityConfig.stances)

  function groundSlam:init()
    self:reset()
    self.cooldownTimer = self.cooldownTime
  end

  function groundSlam:update(dt, fireMode, shiftHeld)
    WeaponAbility.update(self, dt, fireMode, shiftHeld)

    self.cooldownTimer = math.max(0, self.cooldownTimer - self.dt)

    if self.weapon.currentAbility == nil
      and self.cooldownTimer == 0
      and self.fireMode == "alt"
      and not mcontroller.onGround()
      and status.overConsumeResource("energy", self.energyUsage) then

      self:setState(self.windup)
    end
  end

  function groundSlam:windup()
    self.weapon:setStance(self.stances.windup)
    self.weapon:updateAim()

    util.wait(self.stances.windup.duration)

    self:setState(self.slam)
  end

  function groundSlam:slam()
    local preSlamPosition = self:slamPosition()
    self.weapon:setStance(self.stances.slam)
    self.weapon:updateAim()
    local postSlamPosition = self:slamPosition()

    animator.playSound("groundSlamFall")
    status.setPersistentEffects("groundSlam", { {stat = "fallDamageMultiplier", effectiveMultiplier = 0}, { stat = "invulnerable", amount = 1 } })

    local lastSlamPosition = self:slamPosition()
    util.wait(self.maxSlamTime, function(dt)
      mcontroller.setYVelocity(self.slamSpeed)
      local newSlamPosition = self:slamPosition()
      if world.lineTileCollision(lastSlamPosition, newSlamPosition) then
        local params = copy(self.projectileParameters)
        params.powerMultiplier = activeItem.ownerPowerMultiplier()
        params.power = params.power * item.instanceValue("damageLevelMultiplier")

        world.spawnProjectile(self.projectileType, lastSlamPosition, activeItem.ownerEntityId(), {0,0}, false, params)
        return true
      end
      lastSlamPosition = newSlamPosition

      if mcontroller.onGround() then return true end

      local damageArea = partDamageArea("blade")
      self.weapon:setDamage(self.damageConfig, damageArea)
    end)

    self.cooldownTimer = self.cooldownTime

    util.wait(self.winddownTime)
  end

  function groundSlam:slamPosition()
    return vec2.add(activeItem.handPosition(animator.partPoint("blade", "groundSlamPoint")), mcontroller.position())
  end

  function groundSlam:reset()
    status.clearPersistentEffects("groundSlam")
    animator.setGlobalTag("directives", "")
  end

  function groundSlam:uninit()
    self:reset()
  end

  return groundSlam
end
