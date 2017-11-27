function update()
end

function drawables()
  local targets = activeItemAnimation.animationParameter("targets")
  local drawables = {}
  if targets then
    for _,targetId in ipairs(targets) do
      local position = world.entityPosition(targetId)
      table.insert(drawables, {
        image = "/items/active/weapons/ranged/altabilities/homingrocket/targetoverlay.png",
        position = world.entityPosition(targetId)
      })
    end
  end
  return drawables
end