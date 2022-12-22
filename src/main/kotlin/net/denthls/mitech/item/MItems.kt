package net.denthls.mitech.item

import net.denthls.mitech.MiTech
import net.denthls.mitech.item.custom.Sampler
import net.denthls.mitech.item.custom.SuperIngot
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import net.minecraft.util.registry.Registry
import org.slf4j.LoggerFactory


object MItems {

    // Items

    val NEUTRON_INGOT = register(
        "neutron_ingot",
        Item(FabricItemSettings().group(MiTech.itemGroup).maxCount(64).fireproof().rarity(Rarity.EPIC))
    )
    val SUPER_INGOT = register(
        "super_ingot",
        SuperIngot(
            FabricItemSettings().group(MiTech.itemGroup).maxCount(1).maxDamage(300).fireproof().rarity(Rarity.EPIC)
        )
    )
    val SAMPLER = register(
        "sampler",
        Sampler(FabricItemSettings().group(MiTech.itemGroup).maxCount(1).maxDamage(1200).rarity(Rarity.UNCOMMON))
    )

    private fun register(name: String, item: Item) = Registry.register(Registry.ITEM, Identifier(MiTech.MI, name), item)

    fun init() {
        LoggerFactory.getLogger("Items: Success")
    }
}
