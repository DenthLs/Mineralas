package net.denthls.mineralas.item

import net.denthls.mineralas.Mineralas
import net.denthls.mineralas.item.custom.DowsingRod
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import net.minecraft.util.registry.Registry


object MnItems {

    private val itemGroup: ItemGroup = FabricItemGroupBuilder.build(
        Identifier(Mineralas.MI, "items")
    ) { ItemStack(DOWSING_ROD) }

    private val DOWSING_ROD: Item = register(
        "dowsing_rod",
        DowsingRod(FabricItemSettings().group(itemGroup).maxCount(1).maxDamage(160).rarity(Rarity.UNCOMMON), 64)
    )
    private val ADVANCED_DOWSING_ROD: Item = register(
        "advanced_dowsing_rod",
        DowsingRod(FabricItemSettings().group(itemGroup).maxCount(1).maxDamage(380).rarity(Rarity.UNCOMMON), 128)
    )

    private fun register(name: String, item: Item) =
        Registry.register(Registry.ITEM, Identifier(Mineralas.MI, name), item)

}
