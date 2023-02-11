package net.denthls.mineralas.world.feature

import net.denthls.mineralas.Mineralas
import net.denthls.mineralas.config.Config
import net.denthls.mineralas.config.ConfigManager
import net.denthls.mineralas.world.feature.featureConfigs.MnFeatureConfig
import net.denthls.mineralas.world.feature.featureConfigs.SampleFeatureConfig
import net.denthls.mineralas.world.feature.removeWorldGen.RemoveVeinFeature
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.loader.api.FabricLoader
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

    private val MI = "modern_industrialization"
    private val TR = "techreborn"
    private val M = "minecraft"
    private val PW = "powah"
    private val IR = "indrev"

    private val deposits = listOf(
        featureList("$M:iron_ore", "iron"),
        featureList("$M:copper_ore", "copper"),
        featureList("$M:coal_ore", "coal"),
        featureList("$M:lapis_ore", "lapis"),
        featureList("$M:gold_ore", "gold"),
        featureList("$M:redstone_ore", "redstone"),
        featureList("$M:diamond_ore", "diamond"),
        featureList("$M:emerald_ore", "emerald"),
        featureList("$MI:antimony_ore", "antimony"),
        featureList("$MI:bauxite_ore", "bauxite"),
        featureList("$MI:iridium_ore", "iridium"),
        featureList("$MI:lead_ore", "lead"),
        featureList("$MI:lignite_coal_ore", "coal"),
        featureList("$MI:mozanite_ore", "mozanite"),
        featureList("$MI:nickel_ore", "nickel"),
        featureList("$MI:salt_ore", "salt"),
        featureList("$MI:tin_ore", "tin"),
        featureList("$MI:tungsten_ore", "tungsten"),
        featureList("$MI:uranium_ore", "uranium"),
        featureList("$TR:bauxite_ore", "bauxite"),
        featureList("$TR:lead_ore", "lead"),
        featureList("$TR:tin_ore", "tin"),
        featureList("$TR:galena_ore", "lead"),
        featureList("$TR:ruby_ore", "ruby"),
        featureList("$TR:silver_ore", "silver"),
        featureList("$TR:sapphire_ore", "sapphire"),
        featureList("$PW:uraninite_ore", "uranium"),
        featureList("$PW:uraninite_ore_poor", "uranium"),
        featureList("$PW:uraninite_ore_dense", "uranium"),
        featureList("ae2:quartz_ore", "quartz"),
        featureList("$IR:tungsten_ore", "tungsten"),
        featureList("$IR:tin_ore", "tin"),
        featureList("$IR:silver_ore", "silver"),
        featureList("$IR:lead_ore", "lead"),
        featureList("$IR:nikolite_ore", "nikolite")
        )

    fun generateOres() {
        deposits.forEach {
            register(it)
        }
    }

    private fun featureList(name: String, samplePath: String): List<String> =
        listOf(name + "_feature", samplePath + "_sample", name, samplePath)

    private fun register(list: List<String>) {
        val props: Config.Deposit = depositConfig(list[3])
        if (props.depositEnabled) {
            if (FabricLoader.getInstance()
                    .isModLoaded(Identifier(list[0]).namespace) || Identifier(list[0]).namespace == M
            ) {
                registerFeature(list[0], list[1], list[2], props.depositRarity, props.depositSize)
            }
        }
    }

    private fun registerFeature(
        featureId: String,
        samplePath: String,
        oreId: String,
        rarity: Int,
        size: Int
    ) {
        val id = Identifier(Mineralas.MI, Identifier(featureId).namespace + "_" + Identifier(featureId).path)
        val feature: Feature<MnFeatureConfig> = OreSampleFeature(MnFeatureConfig.CODEC)
        val configuredFeature: ConfiguredFeature<MnFeatureConfig, OreSampleFeature> =
            ConfiguredFeature<MnFeatureConfig, OreSampleFeature>(
                feature as OreSampleFeature,
                MnFeatureConfig(Identifier(Mineralas.MI, samplePath), Identifier(oreId), size)
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
        featurePath: String,
        blockPath: String
    ) {
        val id = Identifier(Mineralas.MI, featurePath)
        val feature: Feature<SampleFeatureConfig> = StoneSampleFeature(SampleFeatureConfig.CODEC)
        val configuredFeature: ConfiguredFeature<SampleFeatureConfig, StoneSampleFeature> =
            ConfiguredFeature<SampleFeatureConfig, StoneSampleFeature>(
                feature as StoneSampleFeature,
                SampleFeatureConfig(
                    Identifier("mineralas", blockPath), ConfigManager.readConfig().stoneSample.sampleRarity
                )
            )

        val placedFeature = PlacedFeature(
            RegistryEntry.of(configuredFeature),
            listOf<PlacementModifier>(CountPlacementModifier.of(1))
        )

        Registry.register(Registry.FEATURE, id, feature)
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature)
        Registry.register(BuiltinRegistries.PLACED_FEATURE, id, placedFeature)

        if (ConfigManager.readConfig().stoneSample.sampleEnabled) {
            BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, id)
            )
        }
    }

    private fun depositConfig(name: String): Config.Deposit {
        return when (name) {
            "iron" -> ConfigManager.readConfig().ironDeposit
            "coal" -> ConfigManager.readConfig().coalDeposit
            "copper" -> ConfigManager.readConfig().copperDeposit
            "gold" -> ConfigManager.readConfig().goldDeposit
            "lapis" -> ConfigManager.readConfig().lapisDeposit
            "redstone" -> ConfigManager.readConfig().redstoneDeposit
            "diamond" -> ConfigManager.readConfig().diamondDeposit
            "emerald" -> ConfigManager.readConfig().emeraldDeposit
            "antimony" -> ConfigManager.readConfig().antimonyDeposit
            "bauxite" -> ConfigManager.readConfig().bauxiteDeposit
            "tin" -> ConfigManager.readConfig().tinDeposit
            "iridium" -> ConfigManager.readConfig().iridiumDeposit
            "lead" -> ConfigManager.readConfig().leadDeposit
            "lignite_coal" -> ConfigManager.readConfig().ligniteCoalDeposit
            "mozanite" -> ConfigManager.readConfig().mozaniteDeposit
            "nickel" -> ConfigManager.readConfig().nickelDeposit
            "salt" -> ConfigManager.readConfig().saltDeposit
            "tungsten" -> ConfigManager.readConfig().tungstenDeposit
            "uranium" -> ConfigManager.readConfig().uraniumDeposit
            "ruby" -> ConfigManager.readConfig().rubyDeposit
            "sapphire" -> ConfigManager.readConfig().sapphireDeposit
            "silver" -> ConfigManager.readConfig().silverDeposit
            "quartz" -> ConfigManager.readConfig().quartzDeposit
            "nikolite" -> ConfigManager.readConfig().nikoliteDeposit
            else -> ConfigManager.readConfig().ironDeposit
        }
    }

}