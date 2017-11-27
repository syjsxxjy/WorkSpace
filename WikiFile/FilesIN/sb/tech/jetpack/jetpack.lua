function init()
  self.holdingJump = false
  self.active = false
end

function input(args)
  if args.moves["jump"] and mcontroller.jumping() then
    self.holdingJump = true
  elseif not args.moves["jump"] then
    self.holdingJump = false
  end

  if args.moves["jump"] and not mcontroller.canJump() and not self.holdingJump then
    return "jetpack"
  else
    return nil
  end
end

function update(args)
  local jetpackSpeed = tech.parameter("jetpackSpeed")
  local jetpackControlForce = tech.parameter("jetpackControlForce")
  local energyUsagePerSecond = tech.parameter("energyUsagePerSecond")

  if args.actions["jetpack"] and tech.consumeTechEnergy(energyUsagePerSecond * args.dt) then
    tech.setAnimationState("jetpack", "on")
    mcontroller.controlApproachYVelocity(jetpackSpeed, jetpackControlForce)

    if not self.active then
      tech.playSound("activate")
    end
    self.active = true
  else
    self.active = false
    tech.setAnimationState("jetpack", "off")
  end
end
