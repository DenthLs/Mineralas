package net.denthls.mineralas.item

import net.denthls.mineralas.Mineralas
import net.denthls.mineralas.item.custom.MnSampler
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
    ) { ItemStack(SAMPLER) }

    val SAMPLER: Item = register(
        "sampler",
        MnSampler(FabricItemSettings().group(itemGroup).maxCount(1).maxDamage(1200).rarity(Rarity.UNCOMMON))
    )

    private fun register(name: String, item: Item) =
        Registry.register(Registry.ITEM, Identifier(Mineralas.MI, name), item)

    fun registerItems() {}
}
