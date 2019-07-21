require "/scripts/util.lua"
require "/items/active/weapons/weapon.lua"

function setupAltAbility(altAbilityConfig)
  local travelingSlash = WeaponAbility:new(altAbilityConfig, altAbilityConfig.stances)

  function travelingSlash:init()
    self.cooldownTimer = self.cooldownTime
  end

  function travelingSlash:update(dt, fireMode, shiftHeld)
    WeaponAbility.update(self, dt, fireMode, shiftHeld)

    self.cooldownTimer = math.max(0, self.cooldownTimer - dt)

    if self.weapon.currentAbility == nil and self.fireMode == "alt" and self.cooldownTimer == 0 and status.overConsumeResource("energy", self.energyUsage) then
      self:setState(self.windup)
    end
  end

  function travelingSlash:windup()
    self.weapon:setStance(self.stances.windup)
    self.weapon:updateAim()

    util.wait(self.stances.windup.duration)

    self:setState(self.fire)
  end

  function travelingSlash:fire()
    self.weapon:setStance(self.stances.fire)
    self.weapon:updateAim()

    local position = vec2.add(mcontroller.position(), self.projectileOffset)
    local aimVector = {mcontroller.facingDirection(), 0}
    local params = {
      powerMultiplier = activeItem.ownerPowerMultiplier(),
      power = self:damageAmount()
    }
    world.spawnProjectile(self.projectileType, position, activeItem.ownerEntityId(), aimVector, false, params)

    animator.playSound(self.weapon.elementalType.."TravelSlash")

    util.wait(self.stances.fire.duration)
    self.cooldownTimer = self.cooldownTime
  end

  function travelingSlash:damageAmount()
    return self.baseDamage * item.instanceValue("damageLevelMultiplier")
  end

  function travelingSlash:uninit()
  end

  return travelingSlash
end
