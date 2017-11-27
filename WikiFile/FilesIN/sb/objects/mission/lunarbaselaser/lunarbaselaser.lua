function init()
  self.fireTimer = 0
  self.chargeTime = 0.5

  entity.setInteractive(true)

  storage.missionComplete = storage.missionComplete or false
  entity.setOutboundNodeLevel(1, storage.missionComplete)

  self.maxSwitches = 4
end

function onInboundNodeChange(args)
  local activeNodes = activeInboundNodes()
  if args.level and activeNodes < self.maxSwitches then
    entity.playSound("blip")
  elseif args.level then
    entity.playSound("activate")
  end
end

function onInteraction()
  local activeNodes = activeInboundNodes()
  if activeNodes >= self.maxSwitches then
    self.fireTimer = self.chargeTime
  else
    entity.playSound("error")
  end
end

function update(dt)
  local activeNodes = activeInboundNodes()
  if self.fireTimer > 0 and activeNodes == self.maxSwitches then
    if self.fireTimer == self.chargeTime then
      entity.playSound("charge")
    end
    entity.setAnimationState("laserState", "on")
    self.fireTimer = self.fireTimer - dt

    if self.fireTimer < 0 then
      entity.setOutboundNodeLevel(0, true)
      local power = entity.configParameter("projectilePower")
      local projectile = entity.configParameter("projectileType")
      local offset = entity.configParameter("projectileOffset")
      world.spawnProjectile(projectile, entity.toAbsolutePosition(offset), entity.id(), {0, 1}, true, {power = power, damageType = "IgnoresDef"})
      entity.playSound("fire")
    end
  else
    entity.setOutboundNodeLevel(0, false)
    self.fireTimer = 0

    if activeNodes < self.maxSwitches then
      entity.setAnimationState("laserState", "off."..activeNodes)
    else
      entity.setAnimationState("laserState", "charged")
    end
  end
end

function activeInboundNodes()
  local activeNodes = 0
  for i = 0, self.maxSwitches - 1 do
    if entity.getInboundNodeLevel(i) then
      activeNodes = activeNodes + 1
    end
  end
  return activeNodes
end

function openLunarBaseDoor()
  storage.missionComplete = true
  entity.setOutboundNodeLevel(1, true)
end
