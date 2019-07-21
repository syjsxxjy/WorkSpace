function init()
  self.specialLast = false
  self.lastXPosition = nil
  self.angle = 0
  self.active = false
  tech.setVisible(false)

  self.energyPerSecond = tech.parameter("energyCostPerSecond")
  self.dashControlForce = tech.parameter("dashControlForce")
  self.dashSpeed = tech.parameter("dashSpeed")
  self.ballRadius = tech.parameter("ballRadius")
  self.ballFrames = tech.parameter("ballFrames")
  self.ballMovementParams = tech.parameter("ballCustomMovementParameters")
  self.collisionPoly = mcontroller.baseParameters().standingPoly

  self.doubleTapTimers = {
    left = 0,
    right = 0
  }
  self.input = {}
  self.actions = {
    right = "dashRight",
    left = "dashLeft"
  }
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
  for dir,timer in pairs(self.doubleTapTimers) do
    self.doubleTapTimers[dir] = timer - args.dt

    if args.moves[dir] then
      if not self.input[dir] then
        if self.doubleTapTimers[dir] > 0 then
          self.input[dir] = true
          return self.actions[dir]
        else
          self.doubleTapTimers[dir] = tech.parameter("maxDoubleTapTime")
        end
      end
      self.input[dir] = true
    else
      self.input[dir] = false
    end
  end
end

function update(args)
  if (args.actions["dashLeft"] or args.actions["dashRight"]) and mcontroller.onGround() and tech.consumeTechEnergy(self.energyPerSecond * args.dt) then
    self.dashDirection = args.actions["dashLeft"] and "left" or "right"
    local transformPosition = transformPoly(self.ballMovementParams.standingPoly)
    if transformPosition then
      mcontroller.setPosition(transformPosition)
    end
    activate()
  elseif self.active and self.input[self.dashDirection] and tech.consumeTechEnergy(self.energyPerSecond * args.dt) then
    local dir = 1
    if self.dashDirection == "left" then dir = -1 end

    mcontroller.controlParameters(self.ballMovementParams)
    mcontroller.controlApproachXVelocity(dir * self.dashSpeed, self.dashControlForce)

    local angularChange = world.xwrap((self.lastXPosition or mcontroller.xPosition()) - mcontroller.xPosition()) / self.ballRadius
    self.angle = math.fmod(math.pi * 2 + self.angle + angularChange, math.pi * 2)
    local rotationFrame = math.floor(self.angle / math.pi * self.ballFrames) % self.ballFrames
    tech.setGlobalTag("rotationFrame", rotationFrame)
  elseif self.active then
    local transformPosition = transformPoly(self.collisionPoly)
    self.dashDirection = "none"
    if transformPosition then
      mcontroller.setPosition(transformPosition)
      deactivate()
    end
  end

  self.lastXPosition = mcontroller.xPosition()
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
  self.lastXPosition = nil
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
