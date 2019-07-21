function init()
  self.chatOptions = entity.configParameter("chatOptions", {})
  self.chatTimer = 0
end

function update(dt)
  self.chatTimer = math.max(0, self.chatTimer - dt)
  if self.chatTimer == 0 then
    local players = world.entityQuery(entity.position(), entity.configParameter("chatRadius"), {
      includedTypes = {"player"},
      boundMode = "CollisionArea"
    })

    if #players > 0 and #self.chatOptions > 0 then
      entity.say(self.chatOptions[math.random(1, #self.chatOptions)])
      self.chatTimer = entity.configParameter("chatCooldown")
    end
  end

  local chips = world.containerItemAt(entity.id(), 0)
  local controller = world.containerItemAt(entity.id(), 1)
  if chips and chips.name == "autochip" and controller and root.itemHasTag(controller.name, "vehiclecontroller") then
    local healthFactor = controller.parameters.vehicleStartHealthFactor
    if controller.parameters.filled == false then healthFactor = 0 end
    if healthFactor and healthFactor < 1.0 then
      if consumeChip() then
        healthFactor = math.min(healthFactor + entity.configParameter("chipRepairFactor", 1.0), 1.0)
        setControllerHealthFactor(healthFactor)
      end
    end
  end
end

function consumeChip()
  local consumed = world.containerConsumeAt(entity.id(), 0, 1)
  return consumed
end

function setControllerHealthFactor(healthFactor)
  local controller = world.containerItemAt(entity.id(), 1)
  if controller and root.itemHasTag(controller.name, "vehiclecontroller") then
    controller.parameters.filled = true
    controller.parameters.vehicleStartHealthFactor = healthFactor
    world.containerSwapItemsNoCombine(entity.id(), controller, 1)
  end
end