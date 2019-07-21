function init()
  self.drainPos = entity.position()
  if storage.state == nil then
    output(false)
  else
    output(storage.state)
  end
end

-- Change Animation
function output(state)
  if state ~= storage.state then
    storage.state = state
    if state then
      entity.setAnimationState("drainState", "on")
    else
      entity.setAnimationState("drainState", "off")
    end
  end
end

-- Removes Liquids at current position
function drain()
  if world.liquidAt(self.drainPos)then
    world.forceDestroyLiquid(self.drainPos)
  end
end

function update(dt)
  if not entity.isInboundNodeConnected(0) or entity.getInboundNodeLevel(0) then
    output(true)
    drain()
  else
    output(false)
  end
end
