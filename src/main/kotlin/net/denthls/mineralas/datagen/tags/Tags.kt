package net.denthls.mineralas.datagen.tags

import net.minecraft.block.Block
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


object Tags {

    val SAMPLES = registerCommonBlock("samples")
    val MINEABLE = registerMinecraftTag("mineable/pickaxe")

    private fun registerCommonBlock(path: String): TagKey<Block> =
        TagKey.of(Registry.BLOCK_KEY, Identifier("c", path))

    private fun registerMinecraftTag(path: String): TagKey<Block> =
        TagKey.of(Registry.BLOCK_KEY, Identifier("minecraft", path))
}