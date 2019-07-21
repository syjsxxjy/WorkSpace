require "/scripts/util.lua"
require "/items/active/weapons/weapon.lua"

function setupAltAbility(altAbilityConfig, elementalType)
  local primary = item.instanceValue("primaryAttack")
  local altFireAttack = GunFire:new(sb.jsonMerge(primary, altAbilityConfig), altAbilityConfig.stances)

  function altFireAttack:init()
    self.cooldownTimer = self.fireTime
  end

  function altFireAttack:update(dt, fireMode, shiftHeld)
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

  function altFireAttack:muzzleFlash()
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

  function altFireAttack:firePosition()
    if self.firePositionPart then
      return vec2.add(mcontroller.position(), activeItem.handPosition(animator.partPoint(self.firePositionPart, "firePosition")))
    else
      return GunFire.firePosition(self)
    end
  end

  return altFireAttack
end
