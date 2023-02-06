package net.denthls.mineralas

import com.mojang.logging.LogUtils
import net.denthls.mineralas.config.ConfigManager
import net.denthls.mineralas.datagen.tags.Tags
import net.denthls.mineralas.registry.SamplesRegistry
import net.denthls.mineralas.world.feature.ConfiguredFeatures
import net.denthls.mineralas.world.feature.removeWorldGen.RemoveFeature
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import org.slf4j.Logger


object Mineralas : ModInitializer {

    const val MI = "mineralas"
    val logger: Logger = LogUtils.getLogger()

    val samplesGroup: ItemGroup =
        FabricItemGroupBuilder.build(Identifier(MI, "samples")) { ItemStack(SamplesRegistry.STONE_SAMPLE) }

    override fun onInitialize() {
        logger.info("Mineralas Initialized")
        SamplesRegistry
        ConfiguredFeatures.generateOres()
        ConfiguredFeatures.stoneSample("rock", "stone_sample")
        ConfiguredFeatures.removeVein("remove_veins")
        RemoveFeature
        Tags
        ConfigManager
    }
}
