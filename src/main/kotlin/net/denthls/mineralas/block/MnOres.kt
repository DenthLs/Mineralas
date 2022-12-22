package net.denthls.mineralas.block

import net.denthls.mineralas.Mineralas
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object MnOres {

    private val oresGroup: ItemGroup = FabricItemGroupBuilder.build(
        Identifier(Mineralas.MI, "ores")
    ) { ItemStack(HEMATITE_ORE) }

    val HEMATITE_ORE = register(
        "hematite_ore", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)
        )
    )

    private fun register(name: String, block: Block): Block {
        Registry.register(
            Registry.ITEM, Identifier(Mineralas.MI, name),
            BlockItem(block, FabricItemSettings().group(oresGroup))
        )
        return Registry.register(Registry.BLOCK, Identifier(Mineralas.MI, name), block)
    }

    fun registerOres() {}
}