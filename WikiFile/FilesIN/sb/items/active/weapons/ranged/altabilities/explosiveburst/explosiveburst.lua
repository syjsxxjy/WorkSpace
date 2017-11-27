require "/scripts/util.lua"
require "/items/active/weapons/weapon.lua"
require "/items/active/weapons/ranged/gunfire.lua"

function setupAltAbility(altAbilityConfig)
  local primary = item.instanceValue("primaryAttack")
  local explosiveBurst = GunFire:new(sb.jsonMerge(primary, altAbilityConfig), altAbilityConfig.stances)

  function explosiveBurst:init()
    self.cooldownTimer = self.fireTime
  end

  function explosiveBurst:update(dt, fireMode, shiftHeld)
    WeaponAbility.update(self, dt, fireMode, shiftHeld)

    self.cooldownTimer = math.max(0, self.cooldownTimer - self.dt)

    if self.fireMode == "alt"
      and not self.weapon.currentAbility
      and self.cooldownTimer == 0
      and not status.resourceLocked("energy")
      and not world.lineTileCollision(mcontroller.position(), self:firePosition()) then
      
      if self.fireType == "auto" and status.overConsumeResource("energy", self:energyPerShot()) then
        self:setState(self.auto)
      elseif self.fireType == "burst" then
        self:setState(self.burst)
      end
    end
  end

  function explosiveBurst:burst()
    self.aimDistance = world.magnitude(activeItem.ownerAimPosition(), self:firePosition())

    return GunFire.burst(self)
  end

  function explosiveBurst:fireProjectile(...)
    local min = math.max(self.minDistance, self.aimDistance * 0.9) / self.projectileParameters.speed
    local max = math.max(self.minDistance, self.aimDistance * 1.1) / self.projectileParameters.speed
    self.projectileParameters.timeToLive = {min, max}

    GunFire.fireProjectile(self, ...)
  end

  function explosiveBurst:muzzleFlash()
    if not self.hidePrimaryMuzzleFlash then
      animator.setPartTag("muzzleFlash", "variant", math.random(1, 3))
      animator.setAnimationState("firing", "fire")
      animator.setLightActive("muzzleFlash", true)
    end
    
    if self.useParticleEmitter == nil or self.useParticleEmitter then
      animator.burstParticleEmitter("altMuzzleFlash", true)
    end

    if self.usePrimaryFireSound then
      animator.playSound("fire")
    else
      animator.playSound("altFire")
    end
  end

  return explosiveBurst
end
