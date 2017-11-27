function init()
  updateActive()
end

function onInboundNodeChange(args)
  updateActive()
end

function onNodeConnectionChange(args)
  updateActive()
end

function updateActive()
  local active = not entity.isInboundNodeConnected(0) or entity.getInboundNodeLevel(0)
  physics.setForceEnabled("jumpForce", active)
  entity.setAnimationState("padState", active and "on" or "off")
end
