package net.denthls.mineralas.world.feature

import net.denthls.mineralas.Mineralas
import net.denthls.mineralas.config.Config
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

    private val hematiteDeposit = listOf("hematite_feature", "hematite_ore_sample", "mineralas:hematite_ore", "stone")
    private val deepslateHematiteDeposit = listOf("deepslate_hematite_feature", "hematite_ore_sample", "mineralas:deepslate_hematite_ore", "deepslate")
    private val deposits = listOf(hematiteDeposit, deepslateHematiteDeposit)
    private val depositsProps = listOf(Config().hematiteDeposit, Config().deepslateHematiteDeposit)

    fun generateOres() {
        for (i in depositsProps.indices){
            if (depositsProps[i].depositEnabled){
                register(deposits[i], depositsProps[i].depositRarity, depositsProps[i].depositSize)
            }
        }
    }


    private fun register(list: List<String>, rarity: Int, size: Int){
        registerFeature(
            list[0], list[1], list[2], list[3], rarity,
            size
        )
    }
    private fun registerFeature(
        featureId: String,
        sampleId: String,
        oreBlockId: String,
        height: String,
        rarity: Int,
        size: Int
    ) {
        val id = Identifier(Mineralas.MI, featureId)
        val feature: Feature<MnFeatureConfig> = MnOreSampleFeature(MnFeatureConfig.CODEC)
        val configuredFeature: ConfiguredFeature<MnFeatureConfig, MnOreSampleFeature> =
            ConfiguredFeature<MnFeatureConfig, MnOreSampleFeature>(
                feature as MnOreSampleFeature,
                MnFeatureConfig(
                    Identifier(Mineralas.MI, sampleId), Identifier(oreBlockId),
                    size, height
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