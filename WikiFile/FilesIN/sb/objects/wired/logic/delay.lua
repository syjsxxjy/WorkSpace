function init()
  self.queueSize = math.floor(entity.configParameter("delay", 1.0) / script.updateDt() + 0.5)

  self.queue = {}
  for i = 1, self.queueSize do
    self.queue[i] = false
  end
  self.index = 1

  output(false)
end

function update(dt)
  local delayed = self.queue[self.index]
  if delayed ~= self.state then
    output(delayed)
  end
  self.queue[self.index] = entity.getInboundNodeLevel(0)

  self.index = self.index % self.queueSize + 1
end

function output(state)
  self.state = state
  entity.setOutboundNodeLevel(0, state)
  if state then
    entity.setAnimationState("switchState", "on")
  else
    entity.setAnimationState("switchState", "off")
  end
end
