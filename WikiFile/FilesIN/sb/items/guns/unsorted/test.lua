function init()
  world.logInfo("firableItem: init")
end

function uninit()
  world.logInfo("firableItem: uninit")
end

function update(dt)
  world.logInfo("fireableItem: update with dt %s", dt)
end

function fireTriggered()
  world.logInfo("firableItem: fireTriggered")
end

function startTriggered()
  world.logInfo("firableItem: startTriggered")
end

function attemptedFire()
  world.logInfo("firableItem: attemptedFire")
end

function endFire()
  world.logInfo("firableItem: endFire")
end

function triggerWindup()
  world.logInfo("firableItem: triggerWindup")
end

function continueFire()
  world.logInfo("firableItem: continueFire")
end

function triggerCooldown()
  world.logInfo("firableItem: triggerCooldown")
end
