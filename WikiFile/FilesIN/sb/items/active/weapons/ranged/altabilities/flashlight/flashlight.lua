function setupAltAbility(altAbilityConfig)
  local flashlight = WeaponAbility:new(altAbilityConfig, altAbilityConfig.stances)

  function flashlight:init()
    self:reset()
  end

  function flashlight:update(dt, fireMode, shiftHeld)
    WeaponAbility.update(self, dt, fireMode, shiftHeld)

    if self.fireMode == "alt" and self.lastFireMode ~= "alt" then
      self.active = not self.active
      animator.setLightActive("flashlight", self.active)
      animator.setLightActive("flashlightSpread", self.active)
      animator.playSound("flashlight")
    end
    self.lastFireMode = fireMode
  end

  function flashlight:reset()
    animator.setLightActive("flashlight", false)
    animator.setLightActive("flashlightSpread", false)
    self.active = false
  end

  function flashlight:uninit()
    self:reset()
  end

  return flashlight
end