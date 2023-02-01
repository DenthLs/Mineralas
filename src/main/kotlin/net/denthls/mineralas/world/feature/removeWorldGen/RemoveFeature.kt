package net.denthls.mineralas.world.feature.removeWorldGen

import net.denthls.mineralas.Mineralas.log
import net.denthls.mineralas.config.RemoveConfig
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.biome.v1.ModificationPhase
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey

object RemoveFeature {
    fun init(){
        BiomeModifications.create(Identifier("mineralas:remove_features")).add(
            ModificationPhase.REMOVALS, BiomeSelectors.foundInOverworld()
        ) { context: BiomeModificationContext ->
            if (RemoveConfig().defaultFeatures.removeEnabled) {
                for (element in RemoveConfig().defaultFeatures.toRemove) {
                    try {
                        context.generationSettings.removeFeature(
                            RegistryKey.of(
                                Registry.PLACED_FEATURE_KEY,
                                Identifier("minecraft:ore_$element")
                            )
                        )
                    } catch (_: Exception) {
                        log("Feature isn't exist: ore_$element")
                    }
                }
            }
            if (FabricLoader.getInstance().isModLoaded("techreborn")) {
                if (RemoveConfig().techRebornFeatures.removeEnabled) {
                    for (element in RemoveConfig().techRebornFeatures.toRemove) {
                        try {
                            context.generationSettings.removeFeature(
                                RegistryKey.of(
                                    Registry.PLACED_FEATURE_KEY,
                                    Identifier("techreborn:$element")
                                )
                            )
                        } catch (_: Exception) {
                            log("Feature isn't exist: $element")
                        }
                    }
                }
            }
            if (FabricLoader.getInstance().isModLoaded("modern_industrialization")) {
                if (RemoveConfig().modernIndustrializationFeatures.removeEnabled) {
                    for (element in RemoveConfig().modernIndustrializationFeatures.toRemove) {
                        try {
                            context.generationSettings.removeFeature(
                                RegistryKey.of(
                                    Registry.PLACED_FEATURE_KEY,
                                    Identifier("modern_industrialization:ore_generator_$element")
                                )
                            )
                        } catch (_: Exception) {
                            log("Feature isn't exist: ore_generator_$element")
                        }
                        try {
                            context.generationSettings.removeFeature(
                                RegistryKey.of(
                                    Registry.PLACED_FEATURE_KEY,
                                    Identifier("modern_industrialization:deepslate_ore_generator_$element")
                                )
                            )
                        } catch (_: Exception) {
                            log("Feature isn't exist: deepslate_ore_generator_$element")
                        }
                    }
                }
            }
            if (FabricLoader.getInstance().isModLoaded("ae2")) {
                if (RemoveConfig().appliedEnergisticsFeatures.removeEnabled) {
                    for (element in RemoveConfig().appliedEnergisticsFeatures.toRemove) {
                        try {
                            context.generationSettings.removeFeature(
                                RegistryKey.of(
                                    Registry.PLACED_FEATURE_KEY,
                                    Identifier("ae2", element)
                                )
                            )
                        } catch (_: Exception) {
                            log("Feature isn't exist: $element")
                        }
                    }
                }
            }
            if (FabricLoader.getInstance().isModLoaded("powah")) {
                if (RemoveConfig().powahFeatures.removeEnabled) {
                    for (element in RemoveConfig().powahFeatures.toRemove) {
                        try {
                            context.generationSettings.removeFeature(
                                RegistryKey.of(
                                    Registry.PLACED_FEATURE_KEY,
                                    Identifier("powah", element)
                                )
                            )
                        } catch (_: Exception) {
                            log("Feature isn't exist: $element")
                        }
                    }
                }
            }
            if (RemoveConfig().anotherFeatures.removeEnabled){
                for (element in RemoveConfig().anotherFeatures.toRemove) {
                    try {
                        context.generationSettings.removeFeature(
                            RegistryKey.of(
                                Registry.PLACED_FEATURE_KEY,
                                Identifier(element)
                            )
                        )
                    } catch (_: Exception) {
                        log("Feature isn't exist: $element")
                    }
                }
            }
        }
    }
}