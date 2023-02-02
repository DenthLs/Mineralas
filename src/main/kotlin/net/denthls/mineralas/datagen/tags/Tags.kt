package net.denthls.mineralas.datagen.tags

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


object Tags {

    val ORES = registerCommonBlock("ores")
    val ORES_ITEM = registerCommonItem("ores")
    val SAMPLES = registerCommonBlock("samples")
    val MINEABLE = registerMinecraftTag("mineable/pickaxe")

    private fun registerCommonItem(path: String): TagKey<Item> =
        TagKey.of(Registry.ITEM_KEY, Identifier("c", path))

    private fun registerCommonBlock(path: String): TagKey<Block> =
        TagKey.of(Registry.BLOCK_KEY, Identifier("c", path))

    private fun registerMinecraftTag(path: String): TagKey<Block> =
        TagKey.of(Registry.BLOCK_KEY, Identifier("minecraft", path))
}