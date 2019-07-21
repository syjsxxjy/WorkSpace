require "/scripts/vec2.lua"

function die(smash)
  if smash then
    world.spawnProjectile(entity.configParameter("explosionProjectile", fireexplosion), vec2.add(entity.position(), entity.configParameter("explosionOffset", {0,0})), entity.id(), {0,0})
  end
end