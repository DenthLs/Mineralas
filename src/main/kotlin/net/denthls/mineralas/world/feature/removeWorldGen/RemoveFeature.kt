package net.denthls.mineralas.world.feature.removeWorldGen

import net.denthls.mineralas.Mineralas.logger
import net.denthls.mineralas.config.ConfigManager
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.biome.v1.ModificationPhase
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey

object RemoveFeature {
    init {
        BiomeModifications.create(Identifier("mineralas:remove_features")).add(
            ModificationPhase.REMOVALS, BiomeSelectors.foundInOverworld()
        ) { context: BiomeModificationContext ->
            if (ConfigManager.readRemoveConfig().defaultFeatures.removeEnabled) {
                ConfigManager.readRemoveConfig().defaultFeatures.toRemove.forEach { element ->
                    runCatching {
                        context.generationSettings.removeFeature(
                            RegistryKey.of(
                                Registry.PLACED_FEATURE_KEY,
                                Identifier("minecraft:ore_$element")
                            )
                        )
                    }.onFailure {
                        logger.info("Feature isn't exist: ore_$element")
                        logger.info(it.localizedMessage)
                    }
                }
            }
            if (FabricLoader.getInstance().isModLoaded("techreborn")) {
                if (ConfigManager.readRemoveConfig().techRebornFeatures.removeEnabled) {
                    ConfigManager.readRemoveConfig().techRebornFeatures.toRemove.forEach { element ->
                        runCatching {
                            context.generationSettings.removeFeature(
                                RegistryKey.of(
                                    Registry.PLACED_FEATURE_KEY,
                                    Identifier("techreborn:$element")
                                )
                            )
                        }.onFailure {
                            logger.info("Feature isn't exist: $element")
                            logger.info(it.localizedMessage)
                        }
                    }
                }
            }
            if (FabricLoader.getInstance().isModLoaded("modern_industrialization")) {
                if (ConfigManager.readRemoveConfig().modernIndustrializationFeatures.removeEnabled) {
                    ConfigManager.readRemoveConfig().modernIndustrializationFeatures.toRemove.forEach { element ->
                        runCatching {
                            context.generationSettings.removeFeature(
                                RegistryKey.of(
                                    Registry.PLACED_FEATURE_KEY,
                                    Identifier("modern_industrialization:ore_generator_$element")
                                )
                            )
                        }.onFailure {
                            logger.info("Feature isn't exist: ore_generator_$element")
                            logger.info(it.localizedMessage)
                        }
                        runCatching {
                            context.generationSettings.removeFeature(
                                RegistryKey.of(
                                    Registry.PLACED_FEATURE_KEY,
                                    Identifier("modern_industrialization:deepslate_ore_generator_$element")
                                )
                            )
                        }.onFailure {
                            logger.info("Feature isn't exist: deepslate_ore_generator_$element")
                            logger.info(it.localizedMessage)
                        }
                    }
                }
            }
            if (FabricLoader.getInstance().isModLoaded("ae2")) {
                if (ConfigManager.readRemoveConfig().appliedEnergisticsFeatures.removeEnabled) {
                    ConfigManager.readRemoveConfig().appliedEnergisticsFeatures.toRemove.forEach { element ->
                        runCatching {
                            context.generationSettings.removeFeature(
                                RegistryKey.of(
                                    Registry.PLACED_FEATURE_KEY,
                                    Identifier("ae2", element)
                                )
                            )
                        }.onFailure {
                            logger.info("Feature isn't exist: $element")
                            logger.info(it.localizedMessage)
                        }
                    }
                }
            }
            if (FabricLoader.getInstance().isModLoaded("powah")) {
                if (ConfigManager.readRemoveConfig().powahFeatures.removeEnabled) {
                    ConfigManager.readRemoveConfig().powahFeatures.toRemove.forEach { element ->
                        runCatching {
                            context.generationSettings.removeFeature(
                                RegistryKey.of(
                                    Registry.PLACED_FEATURE_KEY,
                                    Identifier("powah", element)
                                )
                            )
                        }.onFailure {
                            logger.info("Feature isn't exist: $element")
                            logger.info(it.localizedMessage)
                        }
                    }
                }
            }
            if (FabricLoader.getInstance().isModLoaded("indrev")) {
                if (ConfigManager.readRemoveConfig().indrevFeatures.removeEnabled) {
                    ConfigManager.readRemoveConfig().indrevFeatures.toRemove.forEach { element ->
                        runCatching {
                            context.generationSettings.removeFeature(
                                RegistryKey.of(
                                    Registry.PLACED_FEATURE_KEY,
                                    Identifier("indrev", element)
                                )
                            )
                        }.onFailure {
                            logger.info("Feature isn't exist: $element")
                            logger.info(it.localizedMessage)
                        }
                    }
                }
            }
            if (ConfigManager.readRemoveConfig().anotherFeatures.removeEnabled) {
                ConfigManager.readRemoveConfig().anotherFeatures.toRemove.forEach { element ->
                    runCatching {
                        context.generationSettings.removeFeature(
                            RegistryKey.of(
                                Registry.PLACED_FEATURE_KEY,
                                Identifier(element)
                            )
                        )
                    }.onFailure {
                        logger.info("Feature isn't exist: $element")
                        logger.info(it.localizedMessage)
                    }
                }
            }
        }
    }
}