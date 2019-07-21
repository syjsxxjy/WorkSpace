require "/scripts/vec2.lua"

function init()
  self.specialLast = false
  self.angularVelocity = 0
  self.lastPosition = nil
  self.angle = 0
  self.active = false
  tech.setVisible(false)

  self.collisionPoly = mcontroller.baseParameters().standingPoly
end

function uninit()
  if self.active then
    local transformPosition = transformPoly(self.collisionPoly)
    if transformPosition then
      mcontroller.setPosition(transformPosition)
    end
    deactivate()
  end
end

function input(args)
  local move = nil
  if args.moves["special"] == 1 and not self.specialLast then
    if self.active then
      move = "morphballDeactivate"
    else
      move = "morphballActivate"
    end
  end

  self.specialLast = args.moves["special"] == 1

  return move
end

function update(args)
  local energyCostPerSecond = tech.parameter("energyCostPerSecond")
  local ballCustomMovementParameters = tech.parameter("ballCustomMovementParameters")
  local ballTransformHeightChange = tech.parameter("ballTransformHeightChange")
  local ballDeactivateCollisionTest = tech.parameter("ballDeactivateCollisionTest")
  local ballRadius = tech.parameter("ballRadius")
  local ballFrames = tech.parameter("ballFrames")

  if not self.active and not tech.parentLounging() and args.actions["morphballActivate"] and tech.consumeTechEnergy(energyCostPerSecond * args.dt) then
    local transformPosition = transformPoly(ballCustomMovementParameters.standingPoly)
    if transformPosition then
      mcontroller.setPosition(transformPosition)
      activate()
    end
  elseif self.active and (args.actions["morphballDeactivate"] or not tech.consumeTechEnergy(energyCostPerSecond * args.dt)) then
    local position = mcontroller.position()
    local resolvedPosition = world.resolvePolyCollision(self.collisionPoly, {position[1], position[2] -ballTransformHeightChange}, 1)
    local transformPosition = transformPoly(self.collisionPoly)
    if transformPosition then
      mcontroller.setPosition(transformPosition)
      deactivate()
    else
      -- Make some kind of error noise if not auto-deactivating
    end
  end

  if self.active then
    mcontroller.controlParameters(ballCustomMovementParameters)

    if mcontroller.onGround() then
      -- If we are on the ground, assume we are rolling without slipping to
      -- determine the angular velocity
      local positionDiff = world.distance(self.lastPosition or mcontroller.position(), mcontroller.position())
      self.angularVelocity = -vec2.mag(positionDiff) / args.dt / ballRadius

      if positionDiff[1] > 0 then
        self.angularVelocity = -self.angularVelocity
      end
    end

    self.angle = math.fmod(math.pi * 2 + self.angle + self.angularVelocity * args.dt, math.pi * 2)

    -- Rotation frames for the ball are given as one *half* rotation so two
    -- full cycles of each of the ball frames completes a total rotation.
    local rotationFrame = math.floor(self.angle / math.pi * ballFrames) % ballFrames
    tech.setGlobalTag("rotationFrame", rotationFrame)
  end

  self.lastPosition = mcontroller.position()
end

function activate()
  tech.setVisible(true)
  tech.burstParticleEmitter("morphballActivateParticles")
  tech.setParentDirectives("?multiply=00000000")
  tech.setToolUsageSuppressed(true)
  self.active = true
end

function deactivate()
  tech.setVisible(false)
  tech.burstParticleEmitter("morphballDeactivateParticles")
  tech.setParentDirectives()
  tech.setToolUsageSuppressed(false)
  self.angle = 0
  self.active = false
end

function transformPoly(toPoly)
  local position = mcontroller.position()
  local yAdjust = collisionBottom(mcontroller.collisionPoly()) - collisionBottom(toPoly)
  return world.resolvePolyCollision(toPoly, {position[1], position[2] + yAdjust}, 1)
end

function collisionBottom(collisionPoly)
  local lowest = 0
  for _,point in pairs(collisionPoly) do
    if point[2] < lowest then
      lowest = point[2]
    end
  end
  return lowest
end
