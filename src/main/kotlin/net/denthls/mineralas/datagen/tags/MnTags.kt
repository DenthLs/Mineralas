package net.denthls.mineralas.datagen.tags

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


object MnTags {

    val ORES = registerCommonBlock("ores")
    val ORES_ITEM = registerCommonItem("ores")
    val SAMPLES = registerCommonBlock("samples")
    val MINEABLE = registerMinecraftTag("mineable/pickaxe")

    private fun registerCommonItem(id: String): TagKey<Item> {
        return TagKey.of(Registry.ITEM_KEY, Identifier("c", id))
    }

    private fun registerCommonBlock(id: String): TagKey<Block> {
        return TagKey.of(Registry.BLOCK_KEY, Identifier("c", id))
    }

    private fun registerMinecraftTag(id: String): TagKey<Block> {
        return TagKey.of(Registry.BLOCK_KEY, Identifier("minecraft", id))
    }
}