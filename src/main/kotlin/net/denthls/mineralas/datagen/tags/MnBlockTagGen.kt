package net.denthls.mineralas.datagen.tags

import net.denthls.mineralas.block.MnOres
import net.denthls.mineralas.block.MnSamples
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Block
import net.minecraft.util.registry.Registry


class MnBlockTagGen(dataGenerator: FabricDataGenerator) :
    FabricTagProvider<Block>(dataGenerator, Registry.BLOCK, "Mineralas Block Tags") {
    override fun generateTags() {
        for (element in ores) {
            getOrCreateTagBuilder(MnTags.ORES)
                .add(element)
        }
        for (element in samples) {
            getOrCreateTagBuilder(MnTags.SAMPLES)
                .add(element)
        }
        getOrCreateTagBuilder(MnTags.HEMATITE_ORES)
            .add(MnOres.HEMATITE_ORE)
//            .add(MnOres.DEEPSLATE_HEMATITE_ORE)
    }

    private val ores = listOf(MnOres.HEMATITE_ORE)
    private val samples = listOf(MnSamples.HEMATITE_ORE_SAMPLE)

}