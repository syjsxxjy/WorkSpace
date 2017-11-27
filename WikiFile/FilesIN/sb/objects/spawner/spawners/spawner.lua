function init()
  entity.setInteractive(false)
  entity.setAnimationState("switchState", "on")
end

function update(dt)
  if world.isVisibleToPlayer(entity.boundBox()) then
    return nil
  end
  local npcSpecies = entity.randomizeParameter("spawner.npcSpeciesOptions")
  local npcType = entity.randomizeParameter("spawner.npcTypeOptions")
  local npcParameter = entity.randomizeParameter("spawner.npcParameterOptions")
  npcParameter.scriptConfig = { spawnedBy = entity.position() }
  world.spawnNpc(entity.toAbsolutePosition({ 0.0, 2.0 }), npcSpecies, npcType, math.max(entity.level(), 1), nil, npcParameter);
  entity.smash()
end
