package net.denthls.mineralas

import net.denthls.mineralas.block.MnOres
import net.denthls.mineralas.block.MnSamples
import net.denthls.mineralas.config.ConfigManager
import net.denthls.mineralas.datagen.tags.MnTags
import net.denthls.mineralas.item.MnItems
import net.denthls.mineralas.world.feature.MnConfiguredFeatures
import net.denthls.mineralas.world.feature.removeWorldGen.AllFeatures
import net.denthls.mineralas.world.feature.removeWorldGen.RemoveFeature
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext.GenerationSettingsContext
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.world.biome.GenerationSettings
import net.minecraft.world.gen.GenerationStep
import org.slf4j.LoggerFactory


object Mineralas : ModInitializer {

    const val MI = "mineralas"
    fun log(name: String) {
        LoggerFactory.getLogger(MI).info(name)
    }
    val oresGroup: ItemGroup = FabricItemGroupBuilder.build(
        Identifier(MI, "ores")
    ) { ItemStack(MnOres.HEMATITE_ORE) }
    val samplesGroup: ItemGroup = FabricItemGroupBuilder.build(
        Identifier(MI, "samples")
    ) { ItemStack(MnSamples.HEMATITE_ORE_SAMPLE) }

    override fun onInitialize() {
        log("Mineralas Initialized")
        MnItems
        MnOres
        MnConfiguredFeatures.generateOres()
        MnTags
        ConfigManager
        MnSamples
        RemoveFeature.init()
    }
}
