require "/scripts/util.lua"

function step()
  self.drawables = {}

  local markerImage = activeItemAnimation.animationParameter("markerImage")
  if markerImage then
    local entities = activeItemAnimation.animationParameter("entities") or {}
    entities = util.filter(entities, world.entityExists)
    for _,entityId in pairs(entities) do
      local drawable = {image = markerImage, position = world.entityPosition(entityId)}
      table.insert(self.drawables, drawable)
    end
  end
end

function drawables()
  return self.drawables or {}
end