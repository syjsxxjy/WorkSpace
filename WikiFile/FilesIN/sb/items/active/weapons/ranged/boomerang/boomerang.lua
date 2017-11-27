require "/scripts/vec2.lua"
require "/scripts/util.lua"

function init()
  self.fireOffset = item.instanceValue("fireOffset")
  self.stances = item.instanceValue("stances")
  self.fireAngleOffset = util.toRadians(item.instanceValue("fireAngleOffset", 0))
  self.projectileType = item.instanceValue("projectileType")
  self.projectileParameters = item.instanceValue("projectileParameters")
  self.projectileParameters.power = self.projectileParameters.power * root.evalFunction("weaponDamageLevelMultiplier", item.instanceValue("level", 1))

  checkProjectiles()
  if storage.projectileIds then
    setStance("throw")
  else
    setStance("idle")
  end
end

function update(dt, fireMode, shiftHeld)
  self.stanceTimer = math.max(self.stanceTimer - dt, 0)

  checkProjectiles()

  if self.stanceName == "idle" then
    if fireMode == "primary" then
      setStance("windup")
    end
  end

  if self.stanceName == "windup" then
    if self.stanceTimer == 0 then
      setStance("throw")
      fire()
    end
  end

  if self.stanceName == "throw" then
    if not storage.projectileIds then
      setStance("catch")
    end
  end

  if self.stanceName == "catch" then
    if self.stanceTimer == 0 then
      setStance("idle")
    end
  end

  updateAim(self.stance.allowRotate, self.stance.allowFlip)
end

function uninit()
  
end

function fire()
  local params = copy(self.projectileParameters)
  params.powerMultiplier = activeItem.ownerPowerMultiplier()
  params.ownerAimPosition = activeItem.ownerAimPosition()
  if self.aimDirection < 0 then params.processing = "?flipx" end
  local projectileId = world.spawnProjectile(
      self.projectileType,
      firePosition(),
      activeItem.ownerEntityId(),
      aimVector(),
      false,
      params
    )
  if projectileId then
    storage.projectileIds = {projectileId}
  end
  animator.playSound("throw")
end

function setStance(stanceName)
  self.stanceName = stanceName
  self.stance = self.stances[stanceName]
  self.stanceTimer = self.stance.duration or 0
  animator.setAnimationState("weapon", stanceName == "throw" and "hidden" or "visible")
  animator.resetTransformationGroup("weapon")
  animator.rotateTransformationGroup("weapon", util.toRadians(self.stance.weaponRotation))
  updateAim(self.stance.allowRotate, self.stance.allowFlip)
end

function checkProjectiles()
  if storage.projectileIds then
    local newProjectileIds = {}
    for i, projectileId in ipairs(storage.projectileIds) do
      if world.entityExists(projectileId) then
        local updatedProjectileIds = world.callScriptedEntity(projectileId, "boomerangProjectileIds")

        if updatedProjectileIds then
          for j, updatedProjectileId in ipairs(updatedProjectileIds) do
            table.insert(newProjectileIds, updatedProjectileId)
          end
        end
      end
    end
    storage.projectileIds = #newProjectileIds > 0 and newProjectileIds or nil
  end
end

function updateAim(allowRotate, allowFlip)
  local aimAngle, aimDirection = table.unpack(activeItem.aimAngleAndDirection(self.fireOffset[2], activeItem.ownerAimPosition()))
  
  if allowRotate then
    self.aimAngle = aimAngle
  end
  aimAngle = (self.aimAngle or 0) + util.toRadians(self.stance.armRotation)
  activeItem.setArmAngle(aimAngle)

  if allowFlip then
    self.aimDirection = aimDirection
  end
  activeItem.setFacingDirection((self.aimDirection or 0))
end

function firePosition()
  return vec2.add(mcontroller.position(), activeItem.handPosition(self.fireOffset))
end

function aimVector()
  local aimVector = vec2.rotate({1, 0}, self.aimAngle + self.fireAngleOffset)
  aimVector[1] = aimVector[1] * self.aimDirection
  return aimVector
end
