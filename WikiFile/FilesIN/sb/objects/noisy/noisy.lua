function init()
  self.sounds = entity.configParameter("sounds", {})
  entity.setSoundPool("noise", self.sounds)
  entity.setInteractive(true)
end

function onInteraction()
  if #self.sounds > 0 then
    entity.playSound("noise")
  end
end

function onNpcPlay(npcId)
  local interact = entity.configParameter("npcToy.interactOnNpcPlayStart")
  if interact == nil or interact ~= false then
    onInteraction()
  end
end
