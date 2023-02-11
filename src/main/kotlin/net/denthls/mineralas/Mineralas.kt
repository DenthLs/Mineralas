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
import net.minecraft.util.registry.Registry
import org.slf4j.Logger
import kotlin.random.Random


object Mineralas : ModInitializer {

    const val MI = "mineralas"
    val logger: Logger = LogUtils.getLogger()

    fun random(value: Float): Boolean {
        return Random.nextFloat() < value
    }

    val samplesGroup: ItemGroup =
        FabricItemGroupBuilder.build(Identifier(MI, "samples")) {
            ItemStack(
                Registry.ITEM.get(
                    Identifier(
                        MI,
                        "stone_sample"
                    )
                )
            )
        }

    val samples = SamplesRegistry.list(SamplesRegistry.samplesId)

    override fun onInitialize() {
        logger.info("Mineralas Initialized")
        SamplesRegistry
        ConfiguredFeatures.generateOres()
        ConfiguredFeatures.stoneSample("rock", "stone_sample")
        ConfiguredFeatures.removeVein("remove_veins")
        RemoveFeature
        Tags
        ConfigManager
        logger.info(samples.toString())
    }
}
