package net.denthls.mineralas.registry
import net.denthls.mineralas.Mineralas.MI
import net.denthls.mineralas.Mineralas.itemGroup
import net.denthls.mineralas.Mineralas.logger
import net.denthls.mineralas.item.DowsingRod
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import net.minecraft.util.registry.Registry

object ItemsRegistry {
    private val ITEMS: MutableMap<Item, Identifier> = LinkedHashMap()

    val DOWSING_ROD = DowsingRod(
        FabricItemSettings()
            .group(itemGroup)
            .maxCount(1)
            .maxDamage(160)
            .rarity(Rarity.UNCOMMON),
        64
    ).create("dowsing_rod")

    val ADVANCED_DOWSING_ROD: Item = DowsingRod(
        FabricItemSettings()
            .group(itemGroup)
            .maxCount(1)
            .maxDamage(380)
            .rarity(Rarity.UNCOMMON),
        128
    ).create("advanced_dowsing_rod")

    fun init() {
        ITEMS.keys.forEach {
            Registry.register(Registry.ITEM, ITEMS[it], it)
            logger.info("Registred item: ${it.name}")
        }
    }

    private fun Item.create(name: String): Item {
        ITEMS[this] = Identifier(MI, name)
        return this
    }
}