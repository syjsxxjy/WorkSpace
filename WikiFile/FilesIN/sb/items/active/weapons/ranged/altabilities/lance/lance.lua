require "/scripts/util.lua"
require "/items/active/weapons/weapon.lua"

function setupAltAbility(altAbilityConfig, elementalType)
  local primary = item.instanceValue("primaryAttack")
  local lanceAttack = WeaponAbility:new(sb.jsonMerge(primary, altAbilityConfig), altAbilityConfig.stances)

  function lanceAttack:init()
    self.cooldownTimer = 0
  end

  function lanceAttack:update(dt, fireMode, shiftHeld)
    WeaponAbility.update(self, dt, fireMode, shiftHeld)

    self.cooldownTimer = math.max(0, self.cooldownTimer - dt)

    if not self.weapon.currentAbility
       and self.fireMode == "alt" 
       and self.cooldownTimer == 0
       and not world.lineTileCollision(mcontroller.position(), self:firePosition())
       and status.overConsumeResource("energy", self.energyUsage) then

      self:setState(self.fire)
    end
  end

  function lanceAttack:fire()
    self.weapon:setStance(self.stances.fire)
    animator.setLightActive(self.weapon.elementalType.."flash", true)
    animator.playSound(self.weapon.elementalType.."lancefire")
    animator.setAnimationState("lance", "fire")

    util.wait(self.stances.fire.duration, function()
      local damageArea = partDamageArea("lance")
      self.weapon:setDamage(self.damageConfig, damageArea)
    end)

    animator.setLightActive(self.weapon.elementalType.."flash", false)
    self.cooldownTimer = self.cooldownTime
  end

  function lanceAttack:firePosition()
    return vec2.add(mcontroller.position(), activeItem.handPosition(self.weapon.muzzleOffset))
  end

  function lanceAttack:uninit()
    
  end

  return lanceAttack
end
