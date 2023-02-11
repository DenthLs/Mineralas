package net.denthls.mineralas.registry

import net.denthls.mineralas.Mineralas
import net.denthls.mineralas.block.SampleBlock
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object SamplesRegistry {

    val samplesId = samplesList(
        listOf(
            "iron", "copper", "coal", "lapis", "redstone",
            "gold", "emerald", "diamond", "antimony", "bauxite", "iridium",
            "lead", "mozanite", "nickel", "salt", "tin", "tungsten",
            "uranium", "ruby", "sapphire", "silver", "quartz", "stone",
            "nikolite"
        )
    )

    init {
        samplesId.forEach {
            register(
                it, SampleBlock(
                    FabricBlockSettings.of(Material.STONE).breakInstantly().noCollision().sounds(BlockSoundGroup.STONE)
                )
            )
        }
    }

    private fun samplesList(original: List<String>): List<String> {
        var list: List<String> = listOf()
        original.forEach {
            list = list.plus(it + "_sample")
        }
        return list
    }

    fun list(original: List<String>): List<Block> {
        var list: List<Block> = listOf()
        original.forEach {
            list = list.plus(Registry.BLOCK.get(Identifier(Mineralas.MI, it)))
        }
        return list
    }

    private fun register(name: String, block: Block) {
        Registry.register(
            Registry.ITEM, Identifier(Mineralas.MI, name),
            BlockItem(block, FabricItemSettings().group(Mineralas.samplesGroup))
        )
        Registry.register(
            Registry.BLOCK, Identifier(Mineralas.MI, name), block
        )
    }

}
