package net.denthls.mineralas

import net.denthls.mineralas.block.MnOres
import net.denthls.mineralas.block.MnSamples
import net.denthls.mineralas.config.ConfigManager
import net.denthls.mineralas.datagen.tags.MnTags
import net.denthls.mineralas.item.MnItems
import net.denthls.mineralas.world.feature.MnConfiguredFeatures
import net.denthls.mineralas.world.feature.removeWorldGen.RemoveFeature
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory


object Mineralas : ModInitializer {

    const val MI = "mineralas"
    fun log(name: String) {
        LoggerFactory.getLogger(MI).info(name)
    }
    val oresGroup: ItemGroup = FabricItemGroupBuilder.build(
        Identifier(MI, "ores")
    ) { ItemStack(MnOres.HEMATITE) }
    val samplesGroup: ItemGroup = FabricItemGroupBuilder.build(
        Identifier(MI, "samples")
    ) { ItemStack(MnSamples.HEMATITE_SAMPLE) }

    val ores = setOf(
        MnOres.HEMATITE, MnOres.LIMONITE, MnOres.MALACHITE, MnOres.AZURITE,
        MnOres.NATIVE_GOLD, MnOres.ULTRABASITE, MnOres.CINNABAR, MnOres.QUARTZ,
        MnOres.ANTIMONITE, MnOres.FOSSIL_COAL, MnOres.ARGENTITE, MnOres.NEVYANSKITE,
        MnOres.CASSITERITE, MnOres.PENTLANDITE, MnOres.HALITE, MnOres.WOLFRAMITE,
        MnOres.URANINITE, MnOres.BAUXITE, MnOres.GALENA, MnOres.MOZANITE
    )
    val samples = setOf(
        MnSamples.HEMATITE_SAMPLE, MnSamples.LIMONITE_SAMPLE, MnSamples.MALACHITE_SAMPLE,
        MnSamples.AZURITE_SAMPLE, MnSamples.NATIVE_GOLD_SAMPLE, MnSamples.ULTRABASITE_SAMPLE,
        MnSamples.CINNABAR_SAMPLE, MnSamples.QUARTZ_SAMPLE, MnSamples.ANTIMONITE_SAMPLE,
        MnSamples.FOSSIL_COAL_SAMPLE, MnSamples.ARGENTITE_SAMPLE, MnSamples.NEVYANSKITE_SAMPLE,
        MnSamples.CASSITERITE_SAMPLE, MnSamples.PENTLANDITE_SAMPLE, MnSamples.HALITE_SAMPLE,
        MnSamples.URANINITE_SAMPLE, MnSamples.BAUXITE_SAMPLE,
        MnSamples.GALENA_SAMPLE, MnSamples.MOZANITE_SAMPLE
    )

    override fun onInitialize() {
        log("Mineralas Initialized")
        MnItems
        MnOres
        MnConfiguredFeatures.generateOres()
        MnConfiguredFeatures.removeVein("remove_veins")
        RemoveFeature.init()
        MnTags
        ConfigManager
        MnSamples
//        AllFeatures.init()
    }
}
