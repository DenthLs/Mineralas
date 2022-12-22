package net.denthls.mineralas.datagen.tags

import net.denthls.mineralas.block.MnOres
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry


class MnItemTagGen(dataGenerator: FabricDataGenerator) :
    FabricTagProvider<Item>(dataGenerator, Registry.ITEM, "Mineralas Block Tags") {

    override fun generateTags() {
        for (element in ores) {
            getOrCreateTagBuilder(MnTags.ORES_ITEM)
                .add(element)
        }
        getOrCreateTagBuilder(MnTags.HEMATITE_ORES_ITEM)
            .add(MnOres.HEMATITE_ORE.asItem())
//            .add(MnOres.DEEPSLATE_HEMATITE_ORE.asItem())
    }

    private val ores = listOf(MnOres.HEMATITE_ORE.asItem())

}