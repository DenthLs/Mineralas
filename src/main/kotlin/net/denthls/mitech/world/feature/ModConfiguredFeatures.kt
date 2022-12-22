package net.denthls.mitech.world.feature

import net.minecraft.block.Blocks
import net.minecraft.util.registry.RegistryEntry
import net.minecraft.world.gen.feature.*

object ModConfiguredFeatures {

    val NEUTRON_BLOCKS: List<OreFeatureConfig.Target> = listOf(
        OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, Blocks.ANCIENT_DEBRIS.defaultState)
    )

    val NEUTRON_BLOCK: RegistryEntry<ConfiguredFeature<OreFeatureConfig, *>> =
        ConfiguredFeatures.register("neutron_block", Feature.ORE, OreFeatureConfig(NEUTRON_BLOCKS, 64))


    fun init(){}

}