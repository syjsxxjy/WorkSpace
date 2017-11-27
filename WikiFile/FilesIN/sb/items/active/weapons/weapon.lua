require "/scripts/util.lua"

-- handles weapon stances, animations, and abilities
Weapon = {}

function Weapon:new(config)
  local newWeapon = config or {}
  newWeapon.damageLevelMultiplier = item.instanceValue("damageLevelMultiplier", root.evalFunction("weaponDamageLevelMultiplier", item.instanceValue("level", 1)))
  newWeapon.elementalType = item.instanceValue("elementalType")
  newWeapon.muzzleOffset = item.instanceValue("muzzleOffset") or {0,0}
  newWeapon.aimOffset = item.instanceValue("aimOffset") or (newWeapon.muzzleOffset[2] - 0.25) -- why is it off by 0.25? nobody knows!
  newWeapon.abilities = {}
  newWeapon.transformationGroups = {}
  setmetatable(newWeapon, extend(self))
  return newWeapon
end

function Weapon:init()
  self.attackTimer = 0
  self.aimAngle = 0
  self.aimDirection = 1

  animator.setGlobalTag("elementalType", self.elementalType or "")

  for _,ability in pairs(self.abilities) do
    ability:init()
  end
end

function Weapon:update(dt, fireMode, shiftHeld)
  self.attackTimer = math.max(0, self.attackTimer - dt)

  for _,ability in pairs(self.abilities) do
    ability:update(dt, fireMode, shiftHeld)
  end

  if self.currentState then
    if coroutine.status(self.stateThread) ~= "dead" then
      local status, result = coroutine.resume(self.stateThread)
      if not status then error(result) end
    else
      self.currentAbility:uninit()
      self.currentAbility = nil
      self.currentState = nil
      self.stateThread = nil
      if self.onLeaveAbility then
        self.onLeaveAbility()
      end
    end
  end

  if self.stance then
    self:updateAim()
  end

  self:clearDamageSources()
end

function Weapon:uninit()
  for _,ability in pairs(self.abilities) do
    if ability.uninit then
      ability:uninit(true)
    end
  end
end

function Weapon:clearDamageSources()
  if not self.damageWasSet and not self.damageCleared then
    activeItem.setItemDamageSources({})
    self.damageCleared = true
  end

  if not self.ownerDamageWasSet and not self.ownerDamageCleared then
    activeItem.setDamageSources({})
    self.ownerDamageCleared = true
  end

  self.damageWasSet = false
  self.ownerDamageWasSet = false
end

function Weapon:setAbilityState(ability, state, ...)
  self.currentAbility = ability
  self.currentState = state
  self.stateThread = coroutine.create(state)
  local status, result = coroutine.resume(self.stateThread, ability, ...)
  if not status then
    error(result)
  end
end

function Weapon:addAbility(newAbility)
  newAbility.weapon = self
  table.insert(self.abilities, newAbility)
end

function Weapon:addTransformationGroup(name, offset, rotation, rotationCenter)
  self.transformationGroups = self.transformationGroups or {}
  table.insert(self.transformationGroups, {name = name, offset = offset, rotation = rotation, rotationCenter = rotationCenter})
end

function Weapon:transformationChanged()
  if compare(self.lastWeaponOffset, self.weaponOffset)
     and compare(self.lastWeaponRotation, self.relativeWeaponRotation)
     and compare(self.lastWeaponRotationCenter, self.relativeWeaponRotationCenter) then
    return false
  else
    self.lastWeaponOffset = self.weaponOffset
    self.lastWeaponRotation = self.relativeWeaponRotation
    self.lastWeaponRotationCenter = self.relativeWeaponRotationCenter
    return true
  end
end

function Weapon:updateAim()
  if self:transformationChanged() then
    for _,group in pairs(self.transformationGroups) do
      animator.resetTransformationGroup(group.name)
      animator.translateTransformationGroup(group.name, group.offset)
      animator.rotateTransformationGroup(group.name, group.rotation, group.rotationCenter)
      animator.translateTransformationGroup(group.name, self.weaponOffset)
      animator.rotateTransformationGroup(group.name, self.relativeWeaponRotation, self.relativeWeaponRotationCenter)
    end
  end

  local aimAngle, aimDirection = table.unpack(activeItem.aimAngleAndDirection(self.aimOffset, activeItem.ownerAimPosition()))
  
  if self.stance.allowRotate then
    self.aimAngle = aimAngle
  end
  activeItem.setArmAngle(self.aimAngle + self.relativeArmRotation)

  if self.stance.allowFlip then
    self.aimDirection = aimDirection
  end
  activeItem.setFacingDirection(self.aimDirection)

  if (self:isFrontHand()) then
    activeItem.setArmFrame(self.stance.frontArmFrame)
  else
    activeItem.setArmFrame(self.stance.backArmFrame)
  end
end

function Weapon:setOwnerDamage(damageConfig, damageArea, damageTimeout)
  self.ownerDamageWasSet = true
  self.ownerDamageCleared = false
  activeItem.setDamageSources({ self:damageSource(damageConfig, damageArea, damageTimeout) })
end

function Weapon:setDamage(damageConfig, damageArea, damageTimeout)
  self.damageWasSet = true
  self.damageCleared = false
  activeItem.setItemDamageSources({ self:damageSource(damageConfig, damageArea, damageTimeout) })
end

function Weapon:damageSource(damageConfig, damageArea, damageTimeout)
  if damageArea then
    local knockback = damageConfig.knockback
    if knockback and damageConfig.knockbackDirectional ~= false then
      knockback = knockbackMomentum(damageConfig.knockback, damageConfig.knockbackMode, self.aimAngle, self.aimDirection)
    end
    local damage = damageConfig.baseDamage * self.damageLevelMultiplier * activeItem.ownerPowerMultiplier()

    return {
      poly = damageArea,
      damage = damage,
      sourceEntity = activeItem.ownerEntityId(),
      team = activeItem.ownerTeam(),
      damageSourceKind = damageConfig.damageSourceKind,
      statusEffects = damageConfig.statusEffects,
      knockback = knockback or 0,
      rayCheck = true,
      damageRepeatGroup = damageRepeatGroup(damageConfig.timeoutGroup),
      damageRepeatTimeout = damageTimeout or damageConfig.timeout
    }
  end
end

function Weapon:setStance(stance)
  self.stance = stance
  self.weaponOffset = stance.weaponOffset or {0,0}
  self.relativeWeaponRotation = util.toRadians(stance.weaponRotation) or 0
  self.relativeWeaponRotationCenter = stance.weaponRotationCenter or {0, 0}
  self.relativeArmRotation = util.toRadians(stance.armRotation) or 0

  if (self:isFrontHand()) then
    activeItem.setArmFrame(stance.frontArmFrame)
  else
    activeItem.setArmFrame(stance.backArmFrame)
  end
  activeItem.setTwoHandedGrip(stance.twoHanded or false)
  activeItem.setRecoil(stance.recoil == true)
end

function Weapon:isFrontHand()
  return (activeItem.hand() == "primary") == (self.aimDirection < 0)
end

function Weapon:faceVector(vector)
  return {vector[1] * self.aimDirection, vector[2]}
end

-- Weapon abilities, state machines for weapon functionality

WeaponAbility = {}

function WeaponAbility:new(config, stances)
  local newAbility = config or {}
  newAbility.stances = stances or {}
  setmetatable(newAbility, extend(self))
  return newAbility
end

function WeaponAbility:update(dt, fireMode, shiftHeld)
  self.dt, self.fireMode, self.shiftHeld = dt, fireMode, shiftHeld
end

function WeaponAbility:setState(state, ...)
  self.weapon:setAbilityState(self, state, ...)
end

function getAltAbility(elementalType)
  local altAbilityConfig = item.instanceValue("altAbility")
  if altAbilityConfig then
    for _, script in ipairs(altAbilityConfig.scripts) do
      require(script)
    end
    return setupAltAbility(altAbilityConfig, elementalType)
  end
end

function partDamageArea(partName, polyName)
  return animator.partPoly(partName, polyName or "damageArea")
end

function damageRepeatGroup(mode)
  mode = mode or ""
  return activeItem.ownerEntityId() .. item.instanceValue("itemName") .. activeItem.hand() .. mode
end

function knockbackMomentum(knockback, knockbackMode, aimAngle, aimDirection)
  knockbackMode = knockbackMode or "aim"

  if type(knockback) == "table" then
    return knockback
  end

  if knockbackMode == "facing" then
    return {aimDirection * knockback, 0}
  elseif knockbackMode == "aim" then
    local aimVector = vec2.rotate({knockback, 0}, aimAngle)
    aimVector[1] = aimDirection * aimVector[1]
    return aimVector
  end
  return knockback
end
