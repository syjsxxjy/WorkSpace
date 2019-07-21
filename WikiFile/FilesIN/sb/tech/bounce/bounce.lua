function init()
  deactivate()
end

function input(args)
  if args.moves["special"] == 1 ~= self.active then
    if self.active then
      return "deactivate"
    else
      return "activate"
    end
  end
end

function update(args)
  local energyUsageRate = tech.parameter("energyUsageRate")
  local bounceCollisionPoly = tech.parameter("bounceCollisionPoly")
  local bounceFactor = tech.parameter("bounceFactor")

  if args.actions["activate"]
      and not tech.parentLounging()
      and world.resolvePolyCollision(bounceCollisionPoly, mcontroller.position(), 1)
      and tech.consumeTechEnergy(energyUsageRate * args.dt) then

    activate()
  elseif args.actions["deactivate"] or (self.active and not tech.consumeTechEnergy(energyUsageRate * args.dt)) then
    deactivate()
  end

  if self.active then
    mcontroller.controlParameters({
      standingPoly = bounceCollisionPoly,
      crouchingPoly = bounceCollisionPoly,
      collisionPoly = bounceCollisionPoly,

      bounceFactor = bounceFactor,
      jumpSpeed = 0
    })
  end
end

function activate()
  status.setPersistentEffects("bounceTech", {{stat = "fallDamageMultiplier", effectiveMultiplier = 0}})
  self.active = true
  tech.setAnimationState("bouncing", "on")
  tech.playSound("activate")
end

function deactivate()
  status.clearPersistentEffects("bounceTech")
  self.active = false
  tech.setAnimationState("bouncing", "off")
end