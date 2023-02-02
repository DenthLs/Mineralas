package net.denthls.mineralas.datagen.tags

import net.denthls.mineralas.Mineralas
import net.denthls.mineralas.block.SampleBlock
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Block
import net.minecraft.util.registry.Registry


class MnBlockTagGen(dataGenerator: FabricDataGenerator) :
    FabricTagProvider<Block>(dataGenerator, Registry.BLOCK, "Mineralas Block Tags") {
    override fun generateTags() {
        Mineralas.ores.forEach { element: Block ->
            getOrCreateTagBuilder(MnTags.ORES).add(element)
            getOrCreateTagBuilder(MnTags.MINEABLE).add(element)
        }
        Mineralas.samples.forEach { element: SampleBlock ->
            getOrCreateTagBuilder(MnTags.SAMPLES).add(element)
            getOrCreateTagBuilder(MnTags.MINEABLE).add(element)
        }

    }
}