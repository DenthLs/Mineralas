package net.denthls.mineralas.world.feature

import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.util.Identifier
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryEntry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier
import net.minecraft.world.gen.placementmodifier.PlacementModifier
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier

object MnConfiguredFeatures {

    fun generateOres() {
//      Real size - size * ~8
//      toReplace - if "0" - stone, if "1" - deepslate
//      Rarity - the lesser, the more to spawn
        register(
            "hematite_feature", "hematite_ore_sample", "mineralas", "hematite_ore", 10,
            32, 0
        )
    }


    private fun register(
        featureId: String,
        sampleId: String,
        oreBlockNameSpace: String,
        oreBlockId: String,
        rarity: Int,
        size: Int,
        toReplace: Int
    ) {
        val id = Identifier("mineralas", featureId)
        val feature: Feature<MnFeatureConfig> = MnOreSampleFeature(MnFeatureConfig.CODEC)
        val configuredFeature: ConfiguredFeature<MnFeatureConfig, MnOreSampleFeature> =
            ConfiguredFeature<MnFeatureConfig, MnOreSampleFeature>(
                feature as MnOreSampleFeature,
                MnFeatureConfig(
                    Identifier("mineralas", sampleId), Identifier(oreBlockNameSpace, oreBlockId),
                    size, toReplace
                )
            )

        val placedFeature = PlacedFeature(
            RegistryEntry.of(
                configuredFeature
            ),
            listOf<PlacementModifier>(CountPlacementModifier.of(1), RarityFilterPlacementModifier.of(rarity))
        )

        Registry.register(Registry.FEATURE, id, feature)
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature)
        Registry.register(BuiltinRegistries.PLACED_FEATURE, id, placedFeature)

        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.VEGETAL_DECORATION,
            RegistryKey.of(Registry.PLACED_FEATURE_KEY, id)
        )

    }

}