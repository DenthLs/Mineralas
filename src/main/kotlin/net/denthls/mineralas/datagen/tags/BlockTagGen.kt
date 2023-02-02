package net.denthls.mineralas.datagen.tags

import net.denthls.mineralas.Mineralas
import net.denthls.mineralas.block.SampleBlock
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Block
import net.minecraft.util.registry.Registry


class BlockTagGen(dataGenerator: FabricDataGenerator) :
    FabricTagProvider<Block>(dataGenerator, Registry.BLOCK, "Mineralas Block Tags") {
    override fun generateTags() {
        Mineralas.ores.forEach { element: Block ->
            getOrCreateTagBuilder(Tags.ORES).add(element)
            getOrCreateTagBuilder(Tags.MINEABLE).add(element)
        }
        Mineralas.samples.forEach { element: SampleBlock ->
            getOrCreateTagBuilder(Tags.SAMPLES).add(element)
            getOrCreateTagBuilder(Tags.MINEABLE).add(element)
        }

    }
}