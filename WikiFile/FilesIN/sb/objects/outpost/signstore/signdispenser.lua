function update(dt)
	if matchingEasel == nil then
		matchingEaselList = world.objectQuery({entity.position()[1]-4,entity.position()[2]},1)
		for i,j in ipairs(matchingEaselList) do
			if world.entityName(j) == "signstore" then matchingEasel = j end
		end
	end
end

function die()
	if matchingEasel ~= nil then world.breakObject(matchingEasel) end
end
