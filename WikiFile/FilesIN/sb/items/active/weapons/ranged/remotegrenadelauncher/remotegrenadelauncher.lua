require "/scripts/vec2.lua"

function init()
  -- scale damage and calculate energy cost
  self.pType = item.instanceValue("projectileType")
  self.pParams = item.instanceValue("projectileParameters", {})
  self.pParams.power = self.pParams.power * root.evalFunction("weaponDamageLevelMultiplier", item.instanceValue("level", 1))
  self.energyPerShot = item.instanceValue("energyUsage")

  self.fireOffset = item.instanceValue("fireOffset")
  updateAim()

  storage.fireTimer = storage.fireTimer or 0
  self.recoilTimer = 0

  self.activeProjectiles = {}
  updateCursor()
end

function activate(fireMode, shiftHeld)
  if fireMode == "alt" then
    triggerProjectiles()
  end
end

function update(dt, fireMode, shiftHeld)
  updateAim()

  storage.fireTimer = math.max(storage.fireTimer - dt, 0)
  self.recoilTimer = math.max(self.recoilTimer - dt, 0)

  if fireMode == "primary"
      and storage.fireTimer <= 0
      and not world.pointTileCollision(firePosition())
      and status.overConsumeResource("energy", self.energyPerShot) then

    storage.fireTimer = item.instanceValue("fireTime", 1.0)
    fire()
  end

  activeItem.setRecoil(self.recoilTimer > 0)

  updateProjectiles()
  updateCursor()
end

function updateCursor()
  if #self.activeProjectiles > 0 then
    activeItem.setCursor("/cursors/chargeready.cursor")
  else
    activeItem.setCursor("/cursors/reticle0.cursor")
  end
end

function uninit()
  for i, projectile in ipairs(self.activeProjectiles) do
    world.callScriptedEntity(projectile, "setTarget", nil)
  end
end

function fire()
  self.pParams.powerMultiplier = activeItem.ownerPowerMultiplier()
  local projectileId = world.spawnProjectile(
      self.pType,
      firePosition(),
      activeItem.ownerEntityId(),
      aimVector(),
      false,
      self.pParams
    )
  if projectileId then
    self.activeProjectiles[#self.activeProjectiles + 1] = projectileId
  end
  animator.burstParticleEmitter("fireParticles")
  animator.playSound("fire")
  self.recoilTimer = item.instanceValue("recoilTime", 0.12)
end

function updateAim()
  self.aimAngle, self.aimDirection = table.unpack(activeItem.aimAngleAndDirection(self.fireOffset[2], activeItem.ownerAimPosition()))
  activeItem.setArmAngle(self.aimAngle)
  activeItem.setFacingDirection(self.aimDirection)
end

function updateProjectiles()
  local newProjectiles = {}
  for i, projectile in ipairs(self.activeProjectiles) do
    if world.entityExists(projectile) then
      newProjectiles[#newProjectiles + 1] = projectile
    end
  end
  self.activeProjectiles = newProjectiles
end

function triggerProjectiles()
  if #self.activeProjectiles > 0 then
    animator.playSound("trigger")
  end
  for i, projectile in ipairs(self.activeProjectiles) do
    world.callScriptedEntity(projectile, "trigger")
  end
end

function firePosition()
  return vec2.add(mcontroller.position(), activeItem.handPosition(self.fireOffset))
end

function aimVector()
  local aimVector = vec2.rotate({1, 0}, self.aimAngle + sb.nrand(item.instanceValue("inaccuracy", 0), 0))
  aimVector[1] = aimVector[1] * self.aimDirection
  return aimVector
end
