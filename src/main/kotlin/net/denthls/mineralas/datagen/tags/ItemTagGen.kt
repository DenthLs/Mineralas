package net.denthls.mineralas.datagen.tags

import net.denthls.mineralas.Mineralas
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry


class ItemTagGen(dataGenerator: FabricDataGenerator) :
    FabricTagProvider<Item>(dataGenerator, Registry.ITEM, "Mineralas Block Tags") {

    override fun generateTags() {
        Mineralas.ores.forEach { element ->
            getOrCreateTagBuilder(Tags.ORES_ITEM).add(element.asItem())
        }
    }
}