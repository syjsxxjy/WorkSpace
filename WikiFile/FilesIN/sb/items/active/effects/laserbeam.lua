require "/scripts/vec2.lua"
require "/scripts/util.lua"

function init()
  
end

function step()

end

function drawables()
  local muzzleOffset = activeItemAnimation.animationParameter("muzzleOffset") or {0,0}

  local muzzlePosition = vec2.add(activeItemAnimation.ownerPosition(), activeItemAnimation.handPosition(muzzleOffset))
  local aimPosition = activeItemAnimation.ownerAimPosition()
  local aimAngle = activeItemAnimation.ownerArmAngle()
  local aimVector = {activeItemAnimation.ownerFacingDirection() * math.cos(aimAngle), math.sin(aimAngle)}

  local lineEnd = vec2.mul(aimVector, 30)

  local blocks = world.collisionBlocksAlongLine(muzzlePosition, vec2.add(muzzlePosition, lineEnd))
  if #blocks > 0 then
    blockPosition = blocks[1]
    --When approaching the block from the right or top, the intersecting edges will be the right or top ones
    if aimVector[1] < 0 then blockPosition[1] = blockPosition[1] + 1 end
    if aimVector[2] < 0 then blockPosition[2] = blockPosition[2] + 1 end

    local blockDistance = world.distance(blockPosition, muzzlePosition)

    -- If the block's edge is in a different direction than the aim direction
    -- it is impossible to have hit that edge, make sure we draw the beam to the other edge
    if aimVector[1] * (blockPosition[1] - muzzlePosition[1]) < 0 then blockDistance[1] = 0 end
    if aimVector[2] * (blockPosition[2] - muzzlePosition[2]) < 0 then blockDistance[2] = 0 end

    -- How long does the beam need to be to move 1 block in each axis
    local deltaDistX = 1 / math.abs(aimVector[1])
    local deltaDistY = 1 / math.abs(aimVector[2])

    -- How long does the beam need to be to reach the collided block on each axis
    local distX = math.abs(blockDistance[1]) * deltaDistX
    local distY = math.abs(blockDistance[2]) * deltaDistY

    -- The largest of the distances is the length of the beam when colliding with the block
    lineEnd = vec2.mul(aimVector, math.max(distX, distY))
  end

  return {{line = {{0,0}, lineEnd}, width = 1, color = {255,255,255}, position = muzzlePosition, fullbright = true}}
end