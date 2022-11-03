package net.denthls.mitech.item

import net.denthls.mitech.MiTech
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

object ModItemsGroup {

    val itemGroup: ItemGroup = FabricItemGroupBuilder.build(
        Identifier(MiTech.MI,"items")) {ItemStack(ModItems.NEUTRON_INGOT)}
    val blockGroup: ItemGroup = FabricItemGroupBuilder.build(
        Identifier(MiTech.MI,"blocks")) {ItemStack(ModItems.NEUTRON_INGOT)}

}