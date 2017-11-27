function init()
  status.setPersistentEffects("coldprotectionTech", {
    {stat = "breathProtection", amount = 1},
    {stat = "biomeradiationImmunity", amount = 1},
    {stat = "biomecoldImmunity", amount = 1}
  })
end

function input(args)
  return nil
end

function uninit()
  status.clearPersistentEffects("coldprotectionTech")
end