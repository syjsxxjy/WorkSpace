function init()
  status.setPersistentEffects("breathprotectionTech", {{stat = "breathProtection", amount = 1}})
end

function input(args)
  return nil
end

function uninit()
  status.clearPersistentEffects("breathprotectionTech")
end