package net.denthls.mineralas.config

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.denthls.mineralas.Mineralas
import java.io.File
import java.nio.file.Paths

object ConfigManager {
    private val json = Json { encodeDefaults = true; prettyPrint = true; ignoreUnknownKeys = true }
    private val configDir: File = Paths.get("", "config", Mineralas.MI).toFile()
    private val configFile = File(configDir, "config.json")

    init {
        if (!configFile.exists()) {
            if (!configDir.exists()) configDir.mkdirs()
            configFile.apply {
                createNewFile()
                writeText(json.encodeToString(Config()))
            }
        } else configFile.writeText(json.encodeToString(read()))
    }

    private fun read(): Config {
        return json.decodeFromString(configFile.readText())
    }

}

