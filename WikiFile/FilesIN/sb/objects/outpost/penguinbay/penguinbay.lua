function init()
  self.detectArea = entity.configParameter("detectArea")
  self.detectArea[1] = entity.toAbsolutePosition(self.detectArea[1])
  self.detectArea[2] = entity.toAbsolutePosition(self.detectArea[2])

  entity.setAnimationState("proximity", "off")
end

function update(dt)
  local players = world.entityQuery(self.detectArea[1], self.detectArea[2], {
      includedTypes = {"player"},
      boundMode = "CollisionArea"
    })

  if #players > 0 and entity.animationState("proximity") == "off" then
    entity.setAnimationState("proximity", "open")
  elseif #players == 0 and entity.animationState("proximity") == "on" then
    entity.setAnimationState("proximity", "close")
  end
end

function onInteraction(args)
  local chatOptions = entity.configParameter("chatOptions", {})
  if #chatOptions > 0 then
    entity.say(chatOptions[math.random(1, #chatOptions)])
  end
end
