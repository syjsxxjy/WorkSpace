function init()
  if storage.state == nil then
    output(false)
  else
    output(storage.state)
  end
  if storage.timer == nil then
    storage.timer = 0
  end
  self.interval = entity.configParameter("interval")
end

function output(state)
  if storage.state ~= state then
    storage.state = state
    entity.setAllOutboundNodes(state)
    if state then
      entity.setAnimationState("switchState", "on")
    else
      entity.setAnimationState("switchState", "off")
    end
  else
  end
end

function update(dt)
  if (not entity.isInboundNodeConnected(0)) or entity.getInboundNodeLevel(0) then
    if storage.timer == 0 then
      storage.timer = self.interval
      output(not storage.state)
    else
      storage.timer = storage.timer - 1 
    end
  else
    storage.timer = 0
    output(false)
  end
end