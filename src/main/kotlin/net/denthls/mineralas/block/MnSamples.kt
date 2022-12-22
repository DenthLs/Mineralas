package net.denthls.mineralas.block

import net.denthls.mineralas.Mineralas
import net.denthls.mineralas.block.custom.SampleBlock
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

object MnSamples {

    private val samplesGroup: ItemGroup = FabricItemGroupBuilder.build(
        Identifier(Mineralas.MI, "samples")
    ) { ItemStack(HEMATITE_ORE_SAMPLE) }

    val HEMATITE_ORE_SAMPLE = register(
        "hematite_ore_sample", SampleBlock(
            FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
        )
    )

    private fun register(name: String, block: Block): Block {
        Registry.register(
            Registry.ITEM, Identifier(Mineralas.MI, name),
            BlockItem(block, FabricItemSettings().group(samplesGroup))
        )
        return Registry.register(Registry.BLOCK, Identifier(Mineralas.MI, name), block)
    }

    fun registerSamples() {}

}