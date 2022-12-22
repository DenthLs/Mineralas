package net.denthls.mineralas.world

import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.biome.v1.ModificationPhase
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.OrePlacedFeatures
import net.minecraft.world.gen.feature.PlacedFeature


object RemoveOres {
    private val toRemove = listOf(
        OrePlacedFeatures.ORE_COPPER,
        OrePlacedFeatures.ORE_COPPER_LARGE,
        OrePlacedFeatures.ORE_COAL_LOWER,
        OrePlacedFeatures.ORE_COAL_UPPER,
        OrePlacedFeatures.ORE_IRON_SMALL,
        OrePlacedFeatures.ORE_IRON_MIDDLE,
        OrePlacedFeatures.ORE_IRON_UPPER,
        OrePlacedFeatures.ORE_GOLD,
        OrePlacedFeatures.ORE_GOLD_LOWER,
        OrePlacedFeatures.ORE_GOLD_EXTRA,
        OrePlacedFeatures.ORE_DIAMOND,
        OrePlacedFeatures.ORE_DIAMOND_BURIED,
        OrePlacedFeatures.ORE_DIAMOND_LARGE,
        OrePlacedFeatures.ORE_LAPIS,
        OrePlacedFeatures.ORE_LAPIS_BURIED,
        OrePlacedFeatures.ORE_EMERALD
    )

    fun init() {
        for (element in toRemove) {
            removeFeature(element.key.get(), GenerationStep.Feature.UNDERGROUND_ORES)
        }
    }

    private fun removeFeature(
        placedFeatureRegistryKey: RegistryKey<PlacedFeature>,
        step: GenerationStep.Feature,
    ) {
        BiomeModifications.create(placedFeatureRegistryKey.value).add(
            ModificationPhase.REMOVALS, BiomeSelectors.all()
        ) { context: BiomeModificationContext ->
            context.generationSettings.removeFeature(placedFeatureRegistryKey)
        }
    }
}
