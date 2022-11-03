package net.denthls.mitech.item

import net.denthls.mitech.MiTech
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import net.minecraft.util.registry.Registry


object ModItems{

    val NEUTRON_INGOT = registerItem("neutron_ingot",
        Item(FabricItemSettings().group(ModItemsGroup.itemGroup).maxCount(64).fireproof().rarity(Rarity.EPIC)))
    val NEUTRON_BLOCK = registerBlock("neutron_block",
            Block(FabricBlockSettings.of(Material.METAL).strength(4f).luminance(5).requiresTool()))



    fun registerBlock(name: String, block: Block){
            Registry.register(Registry.BLOCK, Identifier(MiTech.MI, name), block)
            Registry.register(Registry.ITEM, Identifier(MiTech.MI, name),
                BlockItem(block, FabricItemSettings().group(ModItemsGroup.blockGroup)))
    }
    fun registerItem(name: String, item: Item) = Registry.register(Registry.ITEM, Identifier(MiTech.MI, name), item)
    fun registerModItems() = MiTech.LOGGER.debug("Registering Mod Items for " + MiTech.MI)
}