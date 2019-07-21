function init()
  entity.setInteractive(true)
  entity.setAnimationState("sway", "stop")
end

function onInteraction(args)
  entity.setAnimationState("sway", "sway1")
end
