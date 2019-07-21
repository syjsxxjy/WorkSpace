require "/scripts/util.lua"
require "/scripts/status.lua"
require "/items/active/weapons/weapon.lua"

function setupAltAbility(altAbilityConfig)
  local parry = WeaponAbility:new(altAbilityConfig, altAbilityConfig.stances)

  function parry:init()
    self.cooldownTimer = 0
  end

  function parry:update(dt, fireMode, shiftHeld)
    WeaponAbility.update(self, dt, fireMode, shiftHeld)

    self.cooldownTimer = math.max(0, self.cooldownTimer - dt)

    if self.weapon.currentAbility == nil
      and fireMode == "alt"
      and self.cooldownTimer == 0
      and status.overConsumeResource("energy", self.energyUsage) then
      
      self:setState(self.parry)
    end
  end

  function parry:parry()
    self.weapon:setStance(self.stances.parry)
    self.weapon:updateAim()

    status.setPersistentEffects("broadswordParry", {{stat = "shieldHealth", amount = self.shieldHealth}})

    local blockPoly = animator.partPoly("parryShield", "shieldPoly")
    activeItem.setItemShieldPolys({blockPoly})

    animator.setAnimationState("parryShield", "active")
    animator.playSound("guard")

    local damageListener = damageListener("damageTaken", function(notifications)
      for _,notification in pairs(notifications) do
        if notification.sourceEntityId ~= -65536 and notification.damage == 0 then
          animator.playSound("parry")
          animator.setAnimationState("parryShield", "block")
          return
        end
      end
    end)

    util.wait(self.parryTime, function(dt)
      --Interrupt when running out of shield stamina
      if not status.resourcePositive("shieldStamina") then return true end

      damageListener:update()
    end)

    self.cooldownTimer = self.cooldownTime
    activeItem.setItemShieldPolys({})
  end

  function parry:reset()
    animator.setAnimationState("parryShield", "inactive")
    status.clearPersistentEffects("broadswordParry")
    activeItem.setItemShieldPolys({})
  end

  function parry:uninit()
    self:reset()
  end

  return parry
end
