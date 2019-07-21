function init()
  status.setPersistentEffects("heatprotectionTech", {
    {stat = "breathProtection", amount = 1},
    {stat = "biomeradiationImmunity", amount = 1},
    {stat = "biomecoldImmunity", amount = 1},
    {stat = "biomeheatImmunity", amount = 1} 
  })
end

function input(args)
  return nil
end

function uninit()
  status.clearPersistentEffects("heatprotectionTech")
end