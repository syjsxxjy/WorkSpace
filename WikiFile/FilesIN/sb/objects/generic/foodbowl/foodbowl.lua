function update(dt)
  local item = world.containerItemAt(entity.id(), 0)
  if item then
    local itemType = root.itemType(item.name)
    if itemType == "consumable" then
      entity.setAnimationState("bowl", "full")
      return
    end
  end

  entity.setAnimationState("bowl", "empty")
end