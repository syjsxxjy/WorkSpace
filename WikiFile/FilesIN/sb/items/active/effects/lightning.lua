require "/scripts/vec2.lua"

Drawables = {}

function drawLightning(startLine, endLine, displacement, minDisplacement, forks, forkAngleRange, width, color)
  if displacement < minDisplacement then
    local position = startLine
    endLine = vec2.sub(endLine, startLine)
    startLine = {0,0}
    table.insert(Drawables, { line = {startLine, endLine}, width = width, color = color, position = position, fullbright = true})
  else
    local mid = {(startLine[1] + endLine[1]) / 2, (startLine[2] + endLine[2]) / 2}
    mid = vec2.add(mid, randomOffset(displacement))
    drawLightning(startLine, mid, displacement / 2, minDisplacement, forks - 1, forkAngleRange, width, color)
    drawLightning(mid, endLine, displacement / 2, minDisplacement, forks - 1, forkAngleRange, width, color)

    if forks > 0 then
      local direction = vec2.sub(mid, startLine)
      local length = vec2.mag(direction) / 2
      local angle = math.atan(direction[2], direction[1]) + randomInRange(forkAngleRange)
      forkEnd = vec2.mul({math.cos(angle), math.sin(angle)}, length)
      drawLightning(mid, vec2.add(mid, forkEnd), displacement / 2, minDisplacement, forks - 1, forkAngleRange, width, color)
    end
  end
end

function randomInRange(range)
  return -range + math.random() * 2 * range
end

function randomOffset(range)
  return {randomInRange(range), randomInRange(range)}
end

function drawables()
  Drawables = {}

  local tickRate = activeItemAnimation.animationParameter("lightningTickRate") or 25
  local millis = math.floor((os.time() + (os.clock() % 1)) * 1000)
  math.randomseed(math.floor(millis / tickRate))

  local lightningBolts = activeItemAnimation.animationParameter("lightning")
  if lightningBolts then
    for _,bolt in pairs(lightningBolts) do
      bolt.startLine = vec2.add(activeItemAnimation.ownerPosition(), activeItemAnimation.handPosition(bolt.startLine))
      if bolt.endPosition then
        bolt.endLine = vec2.add(bolt.endPosition, bolt.endLine)
        -- Put end point in the same space as the start point to draw properly over the world wrap
        bolt.endLine = vec2.add(bolt.startLine, world.distance(bolt.endLine, bolt.startLine))
      else
        bolt.endLine = vec2.add(activeItemAnimation.ownerPosition(), activeItemAnimation.handPosition(bolt.endLine))
      end
      if bolt.endPointDisplacement then
        bolt.endLine = vec2.add(bolt.endLine, randomOffset(bolt.endPointDisplacement))
      end
      drawLightning(bolt.startLine, bolt.endLine, bolt.displacement, bolt.minDisplacement, bolt.forks, bolt.forkAngleRange, bolt.width, bolt.color)
    end
  end

  if #Drawables > 0 then
    --world.logInfo("Drawable count: %s", #Drawables)
  end
  return Drawables
end