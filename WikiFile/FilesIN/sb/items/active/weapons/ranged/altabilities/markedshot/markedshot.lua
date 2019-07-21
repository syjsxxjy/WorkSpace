require "/scripts/util.lua"
require "/items/active/weapons/weapon.lua"

function setupAltAbility(altAbilityConfig)
  local primary = item.instanceValue("primaryAttack")
  local markedShot = GunFire:new(sb.jsonMerge(primary, altAbilityConfig), altAbilityConfig.stances)

  function markedShot:init()
    self:reset()

    activeItem.setScriptedAnimationParameter("markerImage", "/items/active/weapons/ranged/altabilities/markedshot/targetoverlay.png")

    self.cooldownTimer = self.fireTime
  end

  function markedShot:update(dt, fireMode, shiftHeld)
    WeaponAbility.update(self, dt, fireMode, shiftHeld)

    self.cooldownTimer = math.max(0, self.cooldownTimer - dt)

    if self.fireMode == "alt" and not self.weapon.currentAbility and self.cooldownTimer == 0 and not status.resourceLocked("energy") then
      self:setState(self.aim)
    end
  end

  function markedShot:aim()
    animator.playSound("enterAimMode")

    while self.fireMode == "alt" do
      status.setResource("energyRegenBlock", 1.0)

      self.targets = util.filter(self.targets, world.entityExists)
      if #self.targets < self.maxTargets then
        local newTarget = self:findTarget()
        if newTarget and status.overConsumeResource("energy", self.energyUsage) then
          table.insert(self.targets, newTarget)
          animator.playSound("targetAcquired"..#self.targets)

          activeItem.setScriptedAnimationParameter("entities", self.targets)
        end
      end

      coroutine.yield()
    end

    if #self.targets > 0 and not world.lineTileCollision(mcontroller.position(), self:firePosition()) then
      self:setState(self.fire)
    else
      animator.playSound("disengage")
    end
  end

  function markedShot:fire()
    local projectileId = self:fireProjectile(self.projectileType, {targets = self.targets})

    self:muzzleFlash()

    self:setState(self.cooldown)
  end


  function markedShot:idle()
    activeItem.setScriptedAnimationParameter("entities", {})
    self.targets = {}
    idle()
  end

  function markedShot:reset()
    activeItem.setScriptedAnimationParameter("entities", {})
    self.targets = {}
  end

  function markedShot:uninit()
    self:reset()
  end

  function markedShot:findTarget()
    local nearEntities = world.entityQuery(activeItem.ownerAimPosition(), self.targetQueryDistance, { includedTypes = {"monster", "npc", "player"} })
    nearEntities = util.filter(nearEntities, function(entityId)
      if contains(self.targets, entityId) then
        return false 
      end

      if not world.entityCanDamage(activeItem.ownerEntityId(), entityId) then
        return false
      end

      if world.lineTileCollision(self:firePosition(), world.entityPosition(entityId)) then
        return false
      end

      return true
    end)

    if #nearEntities > 0 then
      return nearEntities[1]
    else
      return false
    end
  end

  return markedShot
end
