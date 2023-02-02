package net.denthls.mineralas.world.feature

import net.denthls.mineralas.Mineralas
import net.denthls.mineralas.config.Config
import net.denthls.mineralas.world.feature.featureConfigs.MnFeatureConfig
import net.denthls.mineralas.world.feature.featureConfigs.SampleFeatureConfig
import net.denthls.mineralas.world.feature.removeWorldGen.RemoveVeinFeature
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.block.Blocks
import net.minecraft.util.Identifier
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryEntry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier
import net.minecraft.world.gen.placementmodifier.PlacementModifier
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier

object ConfiguredFeatures {

    private val hematiteDeposit = featureList("hematite", "stone")
    private val limoniteDeposit = featureList("limonite", "deepslate")
    private val malachiteDeposit = featureList("malachite", "stone")
    private val azuriteDeposit = featureList("azurite", "deepslate")
    private val nativeGoldDeposit = featureList("native_gold", "deepslate")
    private val ultrabasiteDeposit = featureList("ultrabasite", "deepslate")
    private val cinnabarDeposit = featureList("cinnabar", "deepslate")
    private val quartzDeposit = featureList("quartz", "stone")
    private val antimoniteDeposit = featureList("antimonite", "stone")
    private val fossilCoalDeposit = featureList("fossil_coal", "stone")
    private val argentiteDeposit = featureList("argentite", "deepslate")
    private val nevyanskiteDeposit = featureList("nevyanskite", "deepslate")
    private val cassiteriteDeposit = featureList("cassiterite", "stone")
    private val pentlanditeDeposit = featureList("pentlandite", "stone")
    private val haliteDeposit = featureList("halite", "stone")
    private val wolframiteDeposit = featureList("wolframite", "deepslate")
    private val uraniniteDeposit = featureList("uraninite", "deepslate")
    private val bauxiteDeposit = featureList("bauxite", "stone")
    private val galenaDeposit = featureList("galena", "stone")
    private val mozaniteDeposit = featureList("mozanite", "deepslate")

    private val deposits = listOf(
        hematiteDeposit,
        limoniteDeposit,
        malachiteDeposit,
        azuriteDeposit,
        nativeGoldDeposit,
        ultrabasiteDeposit,
        cinnabarDeposit,
        quartzDeposit,
        antimoniteDeposit,
        fossilCoalDeposit,
        argentiteDeposit,
        nevyanskiteDeposit,
        cassiteriteDeposit,
        pentlanditeDeposit,
        haliteDeposit,
        wolframiteDeposit,
        uraniniteDeposit,
        bauxiteDeposit,
        galenaDeposit,
        mozaniteDeposit
    )

    private val depositsProps = listOf(
        Config().hematiteDeposit,
        Config().limoniteDeposit,
        Config().malachiteDeposit,
        Config().azuriteDeposit,
        Config().nativeGoldDeposit,
        Config().ultrabasiteDeposit,
        Config().cinnabarDeposit,
        Config().quartzDeposit,
        Config().antimoniteDeposit,
        Config().fossilCoalDeposit,
        Config().argentiteDeposit,
        Config().nevyanskiteDeposit,
        Config().cassiteriteDeposit,
        Config().pentlanditeDeposit,
        Config().haliteDeposit,
        Config().wolframiteDeposit,
        Config().uraniniteDeposit,
        Config().bauxiteDeposit,
        Config().galenaDeposit,
        Config().mozaniteDeposit
    )

    fun generateOres() {
        depositsProps.indices.forEach { i ->
            if (depositsProps[i].depositEnabled)
                register(deposits[i], depositsProps[i].depositRarity, depositsProps[i].depositSize)
        }
    }

    private fun featureList(name: String, height: String): List<String> =
        listOf(name + "_ore", name + "_sample", name, height)

    private fun register(list: List<String>, rarity: Int, size: Int) {
        registerFeature(list[0], list[1], list[2], list[3], rarity, size)
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
        val feature: Feature<MnFeatureConfig> = OreSampleFeature(MnFeatureConfig.CODEC)
        val configuredFeature: ConfiguredFeature<MnFeatureConfig, OreSampleFeature> =
            ConfiguredFeature<MnFeatureConfig, OreSampleFeature>(
                feature as OreSampleFeature,
                MnFeatureConfig(Identifier(Mineralas.MI, sampleId), Identifier(Mineralas.MI, oreBlockId), size, height)
            )

        val placedFeature = PlacedFeature(
            RegistryEntry.of(configuredFeature),
            listOf<PlacementModifier>(
                CountPlacementModifier.of(1),
                RarityFilterPlacementModifier.of(rarity)
            )
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

    fun removeVein(
        featureId: String
    ) {
        val id = Identifier(Mineralas.MI, featureId)
        val feature: Feature<OreFeatureConfig> = RemoveVeinFeature(OreFeatureConfig.CODEC)
        val configuredFeature: ConfiguredFeature<OreFeatureConfig, RemoveVeinFeature> =
            ConfiguredFeature<OreFeatureConfig, RemoveVeinFeature>(
                feature as RemoveVeinFeature,
                OreFeatureConfig(
                    OreConfiguredFeatures.STONE_ORE_REPLACEABLES, Blocks.AIR.defaultState, 1
                )
            )

        val placedFeature = PlacedFeature(
            RegistryEntry.of(configuredFeature),
            listOf<PlacementModifier>(CountPlacementModifier.of(1))
        )

        Registry.register(Registry.FEATURE, id, feature)
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature)
        Registry.register(BuiltinRegistries.PLACED_FEATURE, id, placedFeature)

        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES,
            RegistryKey.of(Registry.PLACED_FEATURE_KEY, id)
        )

    }

    fun stoneSample(
        featurePath: String
    ) {
        val id = Identifier(Mineralas.MI, featurePath)
        val feature: Feature<SampleFeatureConfig> = StoneSampleFeature(SampleFeatureConfig.CODEC)
        val configuredFeature: ConfiguredFeature<SampleFeatureConfig, StoneSampleFeature> =
            ConfiguredFeature<SampleFeatureConfig, StoneSampleFeature>(
                feature as StoneSampleFeature,
                SampleFeatureConfig(
                    Identifier("mineralas:stone_sample"), 1
                )
            )

        val placedFeature = PlacedFeature(
            RegistryEntry.of(configuredFeature),
            listOf<PlacementModifier>(CountPlacementModifier.of(1))
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