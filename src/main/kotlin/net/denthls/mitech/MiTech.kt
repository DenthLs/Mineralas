package net.denthls.mitech

import net.denthls.mitech.item.ModItems
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
        Identifier(MI,"items")
    ) { ItemStack(ModItems.NEUTRON_INGOT) }
    val blockGroup: ItemGroup = FabricItemGroupBuilder.build(
        Identifier(MI,"blocks")
    ) { ItemStack(ModItems.NEUTRON_BLOCK) }

    override fun onInitialize() {

    ModItems.init()
    //BlockEntities.init()
    ModConfiguredFeatures.init()
    ModOreGeneration.generateOres()

    }

}
