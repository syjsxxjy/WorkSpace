require "/scripts/util.lua"
require "/scripts/status.lua"
require "/items/active/weapons/weapon.lua"

function setupAltAbility(altAbilityConfig)
  local downstab = WeaponAbility:new(altAbilityConfig, altAbilityConfig.stances)

  function downstab:init()
    self.cooldownTimer = self.cooldownTime
  end

  function downstab:update(dt, fireMode, shiftHeld)
    WeaponAbility.update(self, dt, fireMode, shiftHeld)

    self.cooldownTimer = math.max(0, self.cooldownTimer - self.dt)

    if not self.weapon.currentAbility
       and self.cooldownTimer == 0
       and self.fireMode == "alt"
       and not mcontroller.onGround()
       and status.overConsumeResource("energy", self.energyUsage) then

      self:setState(self.hold)
    end
  end

  function downstab:hold()
    self.weapon:setStance(self.stances.hold)

    mcontroller.controlParameters({
      airForce = self.holdAirControl
    })

    util.wait(self.stances.hold.duration)

    while mcontroller.yVelocity() > self.stabVelocity and not mcontroller.onGround() do
      coroutine.yield()
    end

    self:setState(self.stab)
  end

  function downstab:stab()
    self.weapon:setStance(self.stances.stab)
    self.weapon:updateAim()
    
    animator.playSound("downstab")

    local energyDepleted = false
    local damageListener = damageListener("inflictedHits", function()
      mcontroller.setYVelocity(self.bounceYVelocity)
      if status.overConsumeResource("energy", self.energyUsage) then
        self:setState(self.hold)
      else
        energyDepleted = true
      end
    end)

    while self.fireMode == "alt" and not mcontroller.onGround() do
      local damageArea = partDamageArea("blade")
      self.weapon:setDamage(self.damageConfig, damageArea)
      if mcontroller.yVelocity() > 0 then
        self:setState(self.hold)
      end

      damageListener:update()
      if energyDepleted then return end
      coroutine.yield()
    end
  end

  function downstab:uninit()
    self.cooldownTimer = self.cooldownTime
  end

  return downstab
end
