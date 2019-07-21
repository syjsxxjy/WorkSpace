function init()
  storage.timer = storage.timer or 0
  storage.timerDuration = storage.timerDuration or entity.configParameter("duration", 5)

  updateAnimation()

  entity.setInteractive(true)
end

function update(dt)
  if entity.getInboundNodeLevel(0) then
    storage.timer = storage.timerDuration
  elseif storage.timer > 0 then
    storage.timer = math.max(storage.timer - dt, 0)
  end

  entity.setOutboundNodeLevel(0, storage.timer > 0)

  updateAnimation()
end

function updateAnimation()
  if storage.timer == storage.timerDuration then
    entity.setAnimationState("timerState", "on"..storage.timerDuration)
  elseif storage.timer > 0 then
    entity.setAnimationState("timerState", "count"..math.ceil(storage.timer))
  else
    entity.setAnimationState("timerState", "off"..storage.timerDuration)
  end
end

function onInteraction(args)
  storage.timer = 0
  storage.timerDuration = storage.timerDuration + 1
  if storage.timerDuration > 5 then storage.timerDuration = 1 end
end
