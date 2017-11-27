function init()
  storage.levelData = storage.levelData or false

  message.setHandler("save", function(_, _, data)
    save(data)
  end)
  message.setHandler("load", function(_, _)
    return load()
  end)
end

function save(data)
  storage.levelData = data
end

function load(data)
  return storage.levelData
end