package net.denthls.mineralas

import com.mojang.logging.LogUtils
import net.denthls.mineralas.config.ConfigManager
import net.denthls.mineralas.datagen.tags.Tags
import net.denthls.mineralas.registry.ItemsRegistry
import net.denthls.mineralas.registry.ItemsRegistry.DOWSING_ROD
import net.denthls.mineralas.registry.OresRegistry
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


    val oresGroup: ItemGroup = FabricItemGroupBuilder.build(Identifier(MI, "ores")) { ItemStack(OresRegistry.HEMATITE) }
    val samplesGroup: ItemGroup = FabricItemGroupBuilder.build(Identifier(MI, "samples")) { ItemStack(SamplesRegistry.HEMATITE_SAMPLE) }
    val itemGroup: ItemGroup = FabricItemGroupBuilder.build(Identifier(MI, "items")) { ItemStack(DOWSING_ROD) }

    val ores = setOf(
        OresRegistry.HEMATITE, OresRegistry.LIMONITE, OresRegistry.MALACHITE, OresRegistry.AZURITE,
        OresRegistry.NATIVE_GOLD, OresRegistry.ULTRABASITE, OresRegistry.CINNABAR, OresRegistry.QUARTZ,
        OresRegistry.ANTIMONITE, OresRegistry.FOSSIL_COAL, OresRegistry.ARGENTITE, OresRegistry.NEVYANSKITE,
        OresRegistry.CASSITERITE, OresRegistry.PENTLANDITE, OresRegistry.HALITE, OresRegistry.WOLFRAMITE,
        OresRegistry.URANINITE, OresRegistry.BAUXITE, OresRegistry.GALENA, OresRegistry.MOZANITE

)
    val samples = setOf(
        SamplesRegistry.HEMATITE_SAMPLE, SamplesRegistry.LIMONITE_SAMPLE, SamplesRegistry.MALACHITE_SAMPLE,
        SamplesRegistry.AZURITE_SAMPLE, SamplesRegistry.NATIVE_GOLD_SAMPLE, SamplesRegistry.ULTRABASITE_SAMPLE,
        SamplesRegistry.CINNABAR_SAMPLE, SamplesRegistry.QUARTZ_SAMPLE, SamplesRegistry.ANTIMONITE_SAMPLE,
        SamplesRegistry.FOSSIL_COAL_SAMPLE, SamplesRegistry.ARGENTITE_SAMPLE, SamplesRegistry.NEVYANSKITE_SAMPLE,
        SamplesRegistry.CASSITERITE_SAMPLE, SamplesRegistry.PENTLANDITE_SAMPLE, SamplesRegistry.HALITE_SAMPLE,
        SamplesRegistry.URANINITE_SAMPLE, SamplesRegistry.BAUXITE_SAMPLE, SamplesRegistry.GALENA_SAMPLE,
        SamplesRegistry.MOZANITE_SAMPLE
    )

    override fun onInitialize() {
        logger.info("Mineralas Initialized")
        ItemsRegistry.init()
        OresRegistry
        ConfiguredFeatures.generateOres()
        ConfiguredFeatures.stoneSample("rock")
        ConfiguredFeatures.removeVein("remove_veins")
        RemoveFeature
        Tags
        ConfigManager
        SamplesRegistry

//        AllFeatures.init()
    }
}
