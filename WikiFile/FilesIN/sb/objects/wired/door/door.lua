function init()
  setDirection(storage.doorDirection or entity.direction())

  if storage.locked == nil then
    storage.locked = entity.configParameter("locked", false)
  end

  if storage.state == nil then
    closeDoor()
  else
    entity.setAnimationState("doorState", storage.state and "open" or "closed")
  end

  updateInteractive()
  updateCollisionAndWires()
  updateLight()
end

function onNodeConnectionChange(args)
  updateInteractive()
  if entity.isInboundNodeConnected(0) then
    onInboundNodeChange({ level = entity.getInboundNodeLevel(0) })
  end
end

function onInboundNodeChange(args)
  if args.level then
    openDoor(storage.doorDirection)
  else
    closeDoor()
  end
end

function onInteraction(args)
  if storage.locked then
    entity.playSound("locked")
  else
    if not storage.state then
      openDoor(args.source[1])
    else
      closeDoor()
    end
  end
end

function updateLight()
  if not storage.state then
    entity.setLightColor(entity.configParameter("closedLight", {0,0,0,0}))
  else
    entity.setLightColor(entity.configParameter("openLight", {0,0,0,0}))
  end
end

function updateInteractive()
  entity.setInteractive(entity.configParameter("interactive", true) and not entity.isInboundNodeConnected(0))
end

function updateCollisionAndWires()
  entity.setColliding(not storage.state)
  entity.setAllOutboundNodes(storage.state)
end

function setDirection(direction)
  storage.doorDirection = direction
  entity.setGlobalTag("doorDirection", direction < 0 and "Left" or "Right")
end

function hasCapability(capability)
  if entity.isInboundNodeConnected(0) or storage.locked then
    return false
  elseif capability == 'door' then
    return true
  elseif capability == 'closedDoor' then
    return not storage.state
  elseif capability == 'openDoor' then
    return storage.state
  else
    return false
  end
end

function doorOccupiesSpace(position)
  local relative = {position[1] - entity.position()[1], position[2] - entity.position()[2]}
  for _, space in ipairs(entity.spaces()) do
    if math.floor(relative[1]) == space[1] and math.floor(relative[2]) == space[2] then
      return true
    end
  end
  return false
end

function lockDoor()
  if not storage.locked then
    storage.locked = true
    updateInteractive()
    if storage.state then
      -- close door before locking
      storage.state = false
      entity.playSound("close")
      entity.setAnimationState("doorState", "locking")
      updateCollisionAndWires()
    else
      entity.setAnimationState("doorState", "locked")
    end
  end
end

function unlockDoor()
  if storage.locked then
    storage.locked = false
    updateInteractive()
    entity.setAnimationState("doorState", "closed")
  end
end

function closeDoor()
  if storage.state ~= false then
    storage.state = false
    entity.playSound("close")
    entity.setAnimationState("doorState", "closing")
    updateCollisionAndWires()
    updateLight()
  end
end

function openDoor(direction)
  if not storage.state then
    storage.state = true
    storage.locked = false -- make sure we don't get out of sync when wired
    setDirection((direction == nil or direction * entity.direction() < 0) and -1 or 1)
    entity.playSound("open")
    entity.setAnimationState("doorState", "open")
    updateCollisionAndWires()
    updateLight()
  end
end
