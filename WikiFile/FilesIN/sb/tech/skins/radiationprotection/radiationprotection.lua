function init()
  status.setPersistentEffects("radiationprotectionTech", {
    {stat = "breathProtection", amount = 1},
    {stat = "biomeradiationImmunity", amount = 1}
  })
end

function input(args)
  return nil
end

function uninit()
  status.clearPersistentEffects("radiationprotectionTech")
end