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
    private val removeConfigFile = File(configDir, "remove_config.json")

    init {
        if (!configDir.exists()) configDir.mkdirs()
        if (!configFile.exists()) {
            configFile.apply {
                createNewFile()
                writeText(json.encodeToString(Config()))
            }
        } else configFile.writeText(json.encodeToString(readConfig()))
        if (!removeConfigFile.exists()) {
            removeConfigFile.apply {
                createNewFile()
                writeText(json.encodeToString(RemoveConfig()))
            }
        } else removeConfigFile.writeText(json.encodeToString(readRemoveConfig()))
    }

    private fun readConfig(): Config = json.decodeFromString(configFile.readText())

    private fun readRemoveConfig(): RemoveConfig = json.decodeFromString(removeConfigFile.readText())
}

