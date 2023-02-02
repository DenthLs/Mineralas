package net.denthls.mineralas.datagen.tags

import net.denthls.mineralas.Mineralas
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Block
import net.minecraft.util.registry.Registry


class MnBlockTagGen(dataGenerator: FabricDataGenerator) :
    FabricTagProvider<Block>(dataGenerator, Registry.BLOCK, "Mineralas Block Tags") {
    override fun generateTags() {
        for (element in Mineralas.ores) {
            getOrCreateTagBuilder(MnTags.ORES)
                .add(element)
        }
        for (element in Mineralas.samples) {
            getOrCreateTagBuilder(MnTags.SAMPLES)
                .add(element)
        }
    }
}