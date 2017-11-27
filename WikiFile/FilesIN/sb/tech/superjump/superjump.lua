function init()
  self.superJumpTimer = 0
end

function input(args)
  if args.moves["jump"] and args.moves["up"] and mcontroller.onGround() then
    return "superjump"
  else
    return nil
  end
end

function update(args)
  local superJumpSpeed = tech.parameter("superjumpSpeed")
  local superJumpControlForce = tech.parameter("superjumpControlForce")
  local superJumpTime = tech.parameter("superjumpTime")

  if args.actions["superjump"] and mcontroller.onGround() and self.superJumpTimer <= 0 and tech.consumeTechEnergy(tech.parameter("energyUsage")) then
    tech.playSound("jumpSound")
    self.superJumpTimer = superJumpTime
  end

  tech.setFlipped(mcontroller.facingDirection() < 0)

  if self.superJumpTimer > 0 then
    mcontroller.controlApproachYVelocity(superJumpSpeed, superJumpControlForce)
    tech.setParticleEmitterActive("jumpParticles", true)
    self.superJumpTimer = self.superJumpTimer - args.dt
  else
    tech.setParticleEmitterActive("jumpParticles", false)
  end
end
