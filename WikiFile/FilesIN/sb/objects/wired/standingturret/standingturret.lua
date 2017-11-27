require "/scripts/util.lua"
require "/scripts/interp.lua"

function init()
  -- Positions and angles
  self.baseOffset = entity.configParameter("baseOffset")
  self.basePosition = vec2.add(entity.position(), self.baseOffset)
  self.tipOffset = entity.configParameter("tipOffset") --This is offset from BASE position, not object origin

  self.rotationSpeed = util.toRadians(entity.configParameter("rotationSpeed"))
  self.offAngle = util.toRadians(entity.configParameter("offAngle", -30))

  -- Targeting
  self.targetQueryRange = entity.configParameter("targetQueryRange")
  self.targetMinRange = entity.configParameter("targetMinRange")
  self.targetMaxRange = entity.configParameter("targetMaxRange")
  self.targetAngleRange = util.toRadians(entity.configParameter("targetAngleRange"))

  -- Energy
  storage.energy = storage.energy or 0
  self.regenBlockTimer = 0
  self.energyRegen = entity.configParameter("energyRegen")
  self.maxEnergy = entity.configParameter("maxEnergy")
  self.energyRegenBlock = entity.configParameter("energyRegenBlock")

  self.energyBarOffset = entity.configParameter("energyBarOffset")
  self.verticalScaling = entity.configParameter("verticalScaling")
  entity.translateTransformationGroup("energy", self.energyBarOffset)

  -- Initialize turret
  entity.setInteractive(false)

  self.state = FSM:new()
  self.state:set(offState)
end

function update(dt)
  self.state:update(dt)

  world.debugPoint(firePosition(), "green")

  if storage.energy == 0 then
    self.blockEnergyUsage = true
  elseif storage.energy == self.maxEnergy then
    self.blockEnergyUsage = false
  end

  if self.regenBlockTimer > 0 then
    self.regenBlockTimer = math.max(0, self.regenBlockTimer - script.updateDt())
  else
    storage.energy = math.min(self.maxEnergy, storage.energy + self.energyRegen * script.updateDt())
  end

  local ratio = storage.energy / self.maxEnergy
  local animationState = "full"

  if ratio <= 0.75 then animationState = "high" end
  if ratio <= 0.5 then animationState = "medium" end
  if ratio <= 0.25 then animationState = "low" end
  if ratio <= 0 then animationState = "none" end

  local scale = self.verticalScaling and {1, ratio * 11} or {ratio * 11, 1}

  entity.resetTransformationGroup("energy")
  entity.scaleTransformationGroup("energy", scale)
  entity.translateTransformationGroup("energy", self.energyBarOffset)

  entity.setAnimationState("energy", animationState)
end

----------------------------------------------------------------------------------------------------------
-- States

function offState()
  entity.setAnimationState("attack", "dead")
  entity.playSound("powerDown")

  while true do
    entity.rotateGroup("gun", self.offAngle)

    if active() then break end
    coroutine.yield()
  end

  entity.playSound("powerUp")

  self.state:set(scanState)
end

function scanState()
  entity.setAnimationState("attack", "idle")
  util.wait(0.5)
  entity.playSound("scan")

  local timer = 0

  local scanInterval = entity.configParameter("scanInterval")
  local scanAngle = util.toRadians(entity.configParameter("scanAngle"))

  local scan = coroutine.wrap(function()
    while true do
      local target = findTarget()
      if target then return self.state:set(fireState, target) end
      util.wait(1.0)
    end
  end)

  while true do
    timer = timer + script.updateDt() / scanInterval
    if timer > 1 then timer = 0 end
    entity.rotateGroup("gun", scanAngle * math.sin(timer * math.pi*2))

    scan()

    if not active() then break end
    coroutine.yield()
  end

  self.state:set(offState)
end

function fireState(targetId)
  entity.setAnimationState("attack", "attack")
  entity.playSound("foundTarget")

  local maxFireAngle = util.toRadians(entity.configParameter("maxFireAngle"))
  local fire = coroutine.wrap(autoFire)

  while true do
    if not active() then return self.state:set(offState) end

    if not world.entityExists(targetId) then break end

    local targetPosition = world.entityPosition(targetId)
    local toTarget = world.distance(targetPosition, self.basePosition)
    local targetDistance = world.magnitude(toTarget)
    local targetAngle = math.atan(toTarget[2], entity.direction() * toTarget[1])

    if targetDistance > self.targetMaxRange or targetDistance < self.targetMinRange or world.lineTileCollision(self.basePosition, targetPosition) then break end
    if math.abs(targetAngle) > self.targetAngleRange then break end

    entity.rotateGroup("gun", targetAngle)

    local rotation = entity.currentRotationAngle("gun")
    if math.abs(util.angleDiff(targetAngle, rotation)) < maxFireAngle then
      fire()
    end
    coroutine.yield()
  end

  util.wait(1.0)

  self.state:set(scanState)
end

----------------------------------------------------------------------------------------------------------
-- Helping functions, not states

function consumeEnergy(amount)
  if storage.energy <= 0 or self.blockEnergyUsage then return false end
  storage.energy = storage.energy - amount
  self.regenBlockTimer = self.energyRegenBlock
  return true
end

function active()
  if entity.isInboundNodeConnected(0) then
    return entity.getInboundNodeLevel(0)
  end

  storage.active = storage.active ~= nil and storage.active or true
  return storage.active
end

function firePosition()
  local animationPosition = vec2.div(entity.configParameter("animationPosition"), 8)
  local fireOffset = vec2.add(animationPosition, entity.partPoint("gun", "projectileSource"))
  return vec2.add(entity.position(), fireOffset)
end

-- Coroutine
function autoFire()
  local level = math.max(1.0, world.threatLevel())
  local power = entity.configParameter("power", 2)
  power = root.evalFunction("monsterLevelPowerMultiplier", level) * power
  local fireTime = entity.configParameter("fireTime", 0.1)
  local projectileParameters = entity.configParameter("projectileParameters", {})
  local energyUsage = entity.configParameter("energyUsage")

  while true do
    while not consumeEnergy(energyUsage) do coroutine.yield() end

    local rotation = entity.currentRotationAngle("gun")
    local aimVector = {entity.direction() * math.cos(rotation), math.sin(rotation)}
    world.spawnProjectile("standardbullet", firePosition(), entity.id(), aimVector, false, projectileParameters)
    entity.playSound("fire")
    util.wait(fireTime)
  end
end

-- Coroutine
function findTarget()
  local nearEntities = world.entityQuery(self.basePosition, self.targetQueryRange, { includedTypes = { "monster", "npc", "player" } })
  return util.find(nearEntities,  function(entityId)
    local targetPosition = world.entityPosition(entityId)
    if not entity.isValidTarget(entityId) or world.lineTileCollision(self.basePosition, targetPosition) then return false end

    local toTarget = world.distance(targetPosition, self.basePosition)
    local targetAngle = math.atan(toTarget[2], entity.direction() * toTarget[1])
    return world.magnitude(toTarget) > self.targetMinRange and math.abs(targetAngle) < self.targetAngleRange
  end)
end