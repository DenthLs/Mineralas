package net.denthls.mitech.block

import net.denthls.mitech.MiTech
import net.denthls.mitech.block.blockentity.MTBlockEntities
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.slf4j.LoggerFactory

object MTBlocks {

    val COLLECTOR = register(
        "collector_block",
        Collector(
            MTBlockEntities.CollectorBlockEntityFactory,
            FabricBlockSettings.of(Material.METAL).strength(4f).luminance(10).requiresTool()
        )
    )
    val NEUTRON_BLOCK = register(
        "neutron_block",
        Block(FabricBlockSettings.of(Material.METAL).strength(4f).luminance(5).requiresTool())
    )

    private fun register(name: String, block: Block): Block {
        Registry.register(
            Registry.ITEM, Identifier(MiTech.MI, name),
            BlockItem(block, FabricItemSettings().group(MiTech.blockGroup))
        )
        return Registry.register(Registry.BLOCK, Identifier(MiTech.MI, name), block)
    }

    fun init() {
        LoggerFactory.getLogger("Blocks: Success")
    }

}