function init()
  self.switchTime = item.instanceValue("switchTime")
  self.switchTimer = 0
  animator.rotateGroup("parasol", toRadians(-65), true)
  updateAim()
end

function update(dt, fireMode, shiftHeld)
  if fireMode ~= "none" then
    self.switchTimer = math.min(self.switchTimer + dt, self.switchTime)
  else
    self.switchTimer = math.max(0, self.switchTimer - dt)
  end
  if self.switchTimer == self.switchTime then
    animator.setAnimationState("parasol", "open")
    if mcontroller.falling() then
      mcontroller.controlParameters(item.instanceValue("fallingParameters"))
      mcontroller.setYVelocity(math.max(mcontroller.yVelocity(), item.instanceValue("maxFallSpeed")))
    end
  else
    animator.setAnimationState("parasol", "close")
  end
  updateAim()
end

function updateAim()
  self.facingDirection = activeItem.aimAngleAndDirection(0, activeItem.ownerAimPosition())[2]
  activeItem.setFacingDirection(self.facingDirection)
  local armAngle = toRadians(interpolateSigmoid(self.switchTimer / self.switchTime, item.instanceValue("idleRotation"), item.instanceValue("activeRotation")))
  if mcontroller.falling() and self.switchTimer == self.switchTime then
    armAngle = armAngle + mcontroller.velocity()[1] / 100 * self.facingDirection
  end
  activeItem.setArmAngle(armAngle)
end

function toRadians(degrees)
  return (degrees / 180) * math.pi
end

function interpolateSigmoid(offset, value1, value2)
  local sigmoidFactor = sigmoid(12 * (offset - 0.5))
  return value1 + sigmoidFactor * (value2 - value1)
end

function sigmoid(value)
  return 1 / (1 + math.exp(-value));
end