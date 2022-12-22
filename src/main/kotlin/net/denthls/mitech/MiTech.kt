package net.denthls.mitech

import net.denthls.mitech.block.MTBlocks
import net.denthls.mitech.block.blockentity.MTBlockEntities
import net.denthls.mitech.item.MItems
import net.denthls.mitech.world.feature.ModConfiguredFeatures
import net.denthls.mitech.world.gen.ModOreGeneration
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

object MiTech : ModInitializer {

    val MI = "mitech"
    val LOGGER = LoggerFactory.getLogger(MI)

    val itemGroup: ItemGroup = FabricItemGroupBuilder.build(
        Identifier(MI, "items")
    ) { ItemStack(MItems.NEUTRON_INGOT) }
    val blockGroup: ItemGroup = FabricItemGroupBuilder.build(
        Identifier(MI, "blocks")
    ) { ItemStack(MTBlocks.NEUTRON_BLOCK) }

    override fun onInitialize() {

        MTBlocks.init()
        MItems.init()
        MTBlockEntities.init()
        ModConfiguredFeatures.init()
        ModOreGeneration.generateOres()

    }

}
