function init()
  self.placed = true
end

function update(dt)
  if self.placed then
    world.spawnMonster(entity.configParameter("monsterType"), entity.position())
    entity.smash()
  end
end
