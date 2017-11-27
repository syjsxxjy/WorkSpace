require "/scripts/vec2.lua"
require "/scripts/util.lua"

function init()
  self.fireOffset = item.instanceValue("fireOffset", {0,0})
  self.stances = item.instanceValue("stances")
  self.projectileType = item.instanceValue("projectileType")
  self.projectileParameters = item.instanceValue("projectileParameters")
  self.returnSpeed = item.instanceValue("returnSpeed", 0)
  self.returnDistance = item.instanceValue("returnDistance", 1)

  self.previousFireMode = "none"
  self.ropeSegments = {}

  activeItem.setScriptedAnimationParameter("ropeItemOffset",  item.instanceValue("ropeOffset", self.fireOffset))
  activeItem.setScriptedAnimationParameter("ropeWidth", item.instanceValue("ropeWidth"))
  activeItem.setScriptedAnimationParameter("ropeColor", item.instanceValue("ropeColor"))
  activeItem.setScriptedAnimationParameter("ropeFullbright", item.instanceValue("ropeFullbright"))
  activeItem.setScriptedAnimationParameter("ropeSegments", {})

  setStance("idle")
end

function update(dt, fireMode, shiftHeld)
  self.stanceTimer = math.max(self.stanceTimer - dt, 0)

  checkProjectile()

  if self.stanceName == "idle" then
    if fireMode == "primary" and self.previousFireMode ~= "primary" then
      fire()
    end
  elseif self.stanceName == "active" then
    if not self.projectileId or (fireMode == "primary" and self.previousFireMode ~= "primary") then
      resetRope()
    elseif not self.ropeLength and self.anchored then
      setRopeLength()
    else
      updateRope(fireMode ~= "primary")
    end
  end

  self.previousFireMode = fireMode

  updateAim(self.stance.allowRotate, self.stance.allowFlip)
end

function uninit()
  if self.projectileId and world.entityExists(self.projectileId) then
    world.callScriptedEntity(self.projectileId, "kill")
  end
end

function fire()
  local projectileId = world.spawnProjectile(
      self.projectileType,
      firePosition(),
      activeItem.ownerEntityId(),
      aimVector(),
      false,
      self.projectileParameters
    )
  if projectileId then
    self.projectileId = projectileId
    activeItem.setScriptedAnimationParameter("ropeSegments", {"item", self.projectileId})
    setStance("active")
    animator.playSound("fire")
    self.inRange = false
  end
end

function setStance(stanceName)
  self.stanceName = stanceName
  self.stance = self.stances[stanceName]
  self.stanceTimer = self.stance.duration or 0
  animator.setAnimationState("weapon", stanceName == "active" and "empty" or "full")
  animator.rotateGroup("weapon", util.toRadians(self.stance.weaponRotation))
  updateAim(self.stance.allowRotate, self.stance.allowFlip)
end

function ropeVector()
  return world.distance(world.entityPosition(self.projectileId), vec2.add(activeItem.handPosition(item.instanceValue("ropeOffset", self.fireOffset)), mcontroller.position()))
end

function setRopeLength()
  self.ropeLength = vec2.mag(ropeVector())
end

function updateRope(doPull)
  -- TODO: all the actually difficult stuff

  local aimVector = world.distance(world.entityPosition(self.projectileId), mcontroller.position())
  self.aimDirection = aimVector[1] < 0 and -1 or 1
  self.aimAngle = vec2.angle({aimVector[1] * self.aimDirection, aimVector[2]})

  if self.anchored then
    local swingAngle = aimVector[1] > 0 and vec2.angle(aimVector) or math.pi - vec2.angle(aimVector)
    self.inRange = self.inRange or vec2.mag(aimVector) < self.returnDistance
    if not self.inRange and doPull then
      mcontroller.controlApproachVelocityAlongAngle(swingAngle, self.returnSpeed, 1000, true)
    elseif not mcontroller.onGround() then
      if doPull then
        mcontroller.controlApproachVelocity({0, 0}, 100)
      end
      mcontroller.controlApproachVelocityAlongAngle(swingAngle, 0, 1000, true)
    end
  end
end

function resetRope()
  -- sb.logInfo("Cutting the rope!")
  if self.projectileId then
    if world.entityExists(self.projectileId) then
      world.callScriptedEntity(self.projectileId, "kill")
    end
    self.projectileId = nil
  end
  setStance("idle")
  activeItem.setScriptedAnimationParameter("ropeSegments", {})
  self.ropeLength = nil
end

function checkProjectile()
  if self.projectileId then
    if world.entityExists(self.projectileId) then
      self.anchored = world.callScriptedEntity(self.projectileId, "anchored")
    else
      resetRope()
    end
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
  local aimVector = vec2.rotate({1, 0}, self.aimAngle)
  aimVector[1] = aimVector[1] * self.aimDirection
  return aimVector
end
