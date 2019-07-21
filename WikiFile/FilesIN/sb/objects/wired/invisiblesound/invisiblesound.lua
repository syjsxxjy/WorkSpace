function init()
  updateState()
end

function onInboundNodeChange(args)
  updateState()
end

function onNodeConnectionChange(args)
  updateState()
end

function updateState()
  local newState = entity.isInboundNodeConnected(0) and entity.getInboundNodeLevel(0)
  if newState ~= storage.state then
    storage.state = newState
    if storage.state then
      entity.playSound("on")
      entity.setSoundEffectEnabled(true)
      entity.setAnimationState("soundState", "on")
    else
      entity.playSound("off")
      entity.setSoundEffectEnabled(false)
      entity.setAnimationState("soundState", "off")
    end
  end
end
