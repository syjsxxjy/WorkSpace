function init()
  message.setHandler("youwin", function()
      world.spawnItem(entity.configParameter("winningItem"), vec2.add(entity.position(), {0, 3}))
    end)
end
