require "/scripts/vec2.lua"
require "/scripts/poly.lua"

function init()
  storage.cycle = storage.cycle or 0
  storage.dir = storage.dir or 0
  self.platformStart = entity.configParameter("platformStart")
  self.platformEnd = entity.configParameter("platformEnd")
  self.platformMoveTime = entity.configParameter("platformMoveTime")
end

function getMode()
  if not entity.isInboundNodeConnected(0) then
    return "bounce"
  elseif entity.getInboundNodeLevel(0) then
    return "toend"
  else
    return "tostart"
  end
end

function update()
  local mode = getMode()

  local cycle = storage.cycle
  local dir = storage.dir

  if mode == "tostart" then
    if cycle > 0.0 then
      dir = -1
    else
      dir = 0
    end
  elseif mode == "toend" then
    if cycle < 1.0 then
      dir = 1
    else
      dir = 0
    end
  else
    if dir == 0 then
      dir = 1
    end

    if cycle == 1.0 then
      dir = -1
    elseif cycle == 0.0 then
      dir = 1
    end
  end

  cycle = cycle + script.updateDt() / self.platformMoveTime * dir
  cycle = math.min(math.max(cycle, 0.0), 1.0)

  if dir == 0 then
    entity.setAnimationState("moving", "off")
  else
    entity.setAnimationState("moving", "on")
  end

  local pos = vec2.add(vec2.mul(self.platformStart, 1.0 - cycle), vec2.mul(self.platformEnd, cycle))
  physics.setCollisionPosition("platform", pos)
  entity.resetTransformationGroup("platform")
  entity.translateTransformationGroup("platform", pos)

  if dir ~= storage.dir then
    entity.playSound("changeDir")
  end

  storage.cycle = cycle
  storage.dir = dir
end
