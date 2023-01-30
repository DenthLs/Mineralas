package net.denthls.mineralas.world.feature.removeWorldGen

import net.denthls.mineralas.Mineralas.log
import net.fabricmc.fabric.api.biome.v1.BiomeModification
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext.GenerationSettingsContext
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.biome.v1.ModificationPhase
import net.fabricmc.fabric.impl.biome.modification.BiomeSelectionContextImpl
import net.fabricmc.fabric.impl.registry.sync.FabricRegistry
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryEntry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.GenerationSettings
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.carver.ConfiguredCarver
import net.minecraft.world.gen.feature.OrePlacedFeatures
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier
import java.lang.Exception
import java.util.function.Consumer

object RemoveFeature {
    private val miFeatureIds = listOf("antimony", "bauxite", "iridium", "lead", "lignite_coal", "mozanite", "nickel", "salt", "tin", "tungsten", "uranium")
    private val techrebornFeatureIds = listOf("bauxite_ore", "cinnabar_ore", "galena_ore", "sapphire_ore", "lead_ore", "ruby_ore", "silver_ore", "tin_ore")
    private val ae2FeaturesIds = listOf("ae2:quartz_ore")
    private val defaultFeatures = listOf(OrePlacedFeatures.ORE_COPPER, OrePlacedFeatures.ORE_COPPER_LARGE, OrePlacedFeatures.ORE_COAL_LOWER, OrePlacedFeatures.ORE_COAL_UPPER,
        OrePlacedFeatures.ORE_IRON_SMALL, OrePlacedFeatures.ORE_IRON_MIDDLE, OrePlacedFeatures.ORE_IRON_UPPER, OrePlacedFeatures.ORE_GOLD, OrePlacedFeatures.ORE_GOLD_LOWER,
        OrePlacedFeatures.ORE_GOLD_EXTRA, OrePlacedFeatures.ORE_LAPIS, OrePlacedFeatures.ORE_LAPIS_BURIED, OrePlacedFeatures.ORE_REDSTONE, OrePlacedFeatures.ORE_REDSTONE_LOWER,
        OrePlacedFeatures.ORE_DIAMOND, OrePlacedFeatures.ORE_DIAMOND_LARGE, OrePlacedFeatures.ORE_DIAMOND_BURIED, OrePlacedFeatures.ORE_EMERALD)
    fun init(){
        BiomeModifications.create(Identifier("mineralas:remove_features")).add(
            ModificationPhase.REMOVALS, BiomeSelectors.foundInOverworld()
        ) { context: BiomeModificationContext ->
            for (element in defaultFeatures){
                context.generationSettings.removeFeature(element.key.get())
            }
            if (FabricLoader.getInstance().isModLoaded("techreborn")) {
                for (element in techrebornFeatureIds) {
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
            if (FabricLoader.getInstance().isModLoaded("modern_industrialization")) {
                for (element in miFeatureIds) {
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
            if (FabricLoader.getInstance().isModLoaded("ae2")) {
                for (element in ae2FeaturesIds) {
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