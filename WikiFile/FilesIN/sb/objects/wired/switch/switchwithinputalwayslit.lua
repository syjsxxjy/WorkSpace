function init()
  entity.setInteractive(entity.configParameter("interactive", true))
  if storage.state == nil then
    output(entity.configParameter("defaultSwitchState", false))
  else
    output(storage.state)
  end

  if storage.triggered == nil then
    storage.triggered = false
  end
end

function onInteraction(args)
  output(not storage.state)
end

function onNpcPlay(npcId)
  onInteraction()
end

function output(state)
  storage.state = state
  if state then
    entity.setAnimationState("switchState", "on")
    entity.playSound("on");
    entity.setAllOutboundNodes(true)
  else
    entity.setAnimationState("switchState", "off")
    entity.playSound("off");
    entity.setAllOutboundNodes(false)
  end
end

function update(dt)
  if entity.getInboundNodeLevel(0) and not storage.triggered then
    storage.triggered = true
    output(not storage.state)
  elseif storage.triggered and not entity.getInboundNodeLevel(0) then
    storage.triggered = false
  end
end
