package net.denthls.mineralas.datagen.tags

import net.denthls.mineralas.Mineralas
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


object MnTags {

    val ORES = registerCommonBlock("ores")
    val ORES_ITEM = registerCommonItem("ores")
    val SAMPLES = registerCommonBlock("samples")
    val HEMATITE_ORES = registerCommonBlock("hematite_ores")
    val HEMATITE_ORES_ITEM = registerCommonItem("hematite_ores")

    private fun registerItem(id: String): TagKey<Item> {
        return TagKey.of(Registry.ITEM_KEY, Identifier(Mineralas.MI, id))
    }

    private fun registerCommonItem(id: String): TagKey<Item> {
        return TagKey.of(Registry.ITEM_KEY, Identifier("c", id))
    }

    private fun registerBlock(id: String): TagKey<Block> {
        return TagKey.of(Registry.BLOCK_KEY, Identifier(Mineralas.MI, id))
    }

    private fun registerCommonBlock(id: String): TagKey<Block> {
        return TagKey.of(Registry.BLOCK_KEY, Identifier("c", id))
    }

    fun registerTags() {}

}