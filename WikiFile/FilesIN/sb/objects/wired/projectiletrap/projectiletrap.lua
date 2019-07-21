function init()
  storage.timer = storage.timer or 0
  
  if storage.active == nil then
    updateActive()
  end
  entity.setSoundEffectEnabled(storage.active)

  self.fireTime = entity.configParameter("fireTime", 1)
  self.fireTimeVariance = entity.configParameter("fireTimeVariance", 0)
  self.projectile = entity.configParameter("projectile")
  self.projectileConfig = entity.configParameter("projectileConfig", {})
  self.projectilePosition = entity.configParameter("projectilePosition", {0, 0})
  self.projectileDirection = entity.configParameter("projectileDirection", {1, 0})

  self.projectilePosition = entity.toAbsolutePosition(self.projectilePosition)
end

function update(dt)
  if storage.active then
    storage.timer = storage.timer - dt
    if storage.timer <= 0 then
      storage.timer = self.fireTime + (self.fireTimeVariance * math.random() - self.fireTimeVariance / 2)
      shoot()
    end
  end
end

function onNodeConnectionChange(args)
  updateActive()
end

function onInboundNodeChange(args)
  updateActive()
end

function shoot()
  entity.playSound("shoot")
  world.spawnProjectile(self.projectile, self.projectilePosition, entity.id(), self.projectileDirection, false, self.projectileConfig)
end

function updateActive()
  local active = (not entity.isInboundNodeConnected(0)) or entity.getInboundNodeLevel(0)
  if active ~= storage.active then
    storage.active = active
    if active then
      storage.timer = 0
      entity.setAnimationState("trapState", "on")
      entity.setLightColor(entity.configParameter("activeLightColor", {0, 0, 0, 0}))
      entity.setSoundEffectEnabled(true)
      entity.playSound("on");
    else
      entity.setAnimationState("trapState", "off")
      entity.setLightColor(entity.configParameter("inactiveLightColor", {0, 0, 0, 0}))
      entity.setSoundEffectEnabled(false)
      entity.playSound("off");
    end
  end
end
