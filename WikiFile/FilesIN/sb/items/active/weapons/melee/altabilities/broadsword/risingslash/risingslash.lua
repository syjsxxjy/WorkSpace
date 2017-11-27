require "/scripts/util.lua"
require "/items/active/weapons/weapon.lua"

function setupAltAbility(altAbilityConfig)
  local risingSlash = WeaponAbility:new(altAbilityConfig, altAbilityConfig.stances)

  function risingSlash:init()
    self:reset()

    self.cooldownTimer = 0
  end

  function risingSlash:update(dt, fireMode, shiftHeld)
    WeaponAbility.update(self, dt, fireMode, shiftHeld)

    self.cooldownTimer = math.max(0, self.cooldownTimer - dt)

    if self.weapon.currentAbility == nil
      and self.cooldownTimer == 0
      and self.fireMode == "alt"
      and status.overConsumeResource("energy", self.energyUsage) then

      self:setState(self.windup)
    end
  end

  function risingSlash:windup()
    self.weapon:setStance(self.stances.windup)
    self.weapon:updateAim()

    animator.setGlobalTag("directives", "?flipx")

    util.wait(self.stances.windup.duration, function()
      return status.resourceLocked("energy")
    end)

    self:setState(self.slash)
  end

  function risingSlash:slash()
    self.weapon:setStance(self.stances.slash)
    self.weapon:updateAim()

    animator.setAnimationState("risingSwoosh", "slash")
    animator.playSound("upswing")

    util.wait(self.stances.slash.duration, function()
      mcontroller.controlApproachYVelocity(self.dashSpeed, self.dashControlForce)

      local damageArea = partDamageArea("risingSwoosh")
      self.weapon:setDamage(self.damageConfig, damageArea)
    end)

    self.cooldownTimer = self.cooldownTime
    self:setState(self.freeze)
  end

  function risingSlash:freeze()
    self.weapon:setStance(self.stances.freeze)
    self.weapon:updateAim()

    util.wait(self.stances.freeze.duration, function()
      mcontroller.setVelocity({0,0})
    end)
  end
  
  function risingSlash:reset()
    animator.setGlobalTag("directives", "")
  end

  function risingSlash:uninit()
    self:reset()
  end

  return risingSlash
end
