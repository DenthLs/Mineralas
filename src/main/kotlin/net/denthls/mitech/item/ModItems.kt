package net.denthls.mitech.item

import net.denthls.mitech.MiTech
import net.denthls.mitech.block.Collector
import net.denthls.mitech.item.custom.Sampler
import net.denthls.mitech.item.custom.SuperIngot
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

    // Items

    val NEUTRON_INGOT = registerItem("neutron_ingot",
        Item(FabricItemSettings().group(MiTech.itemGroup).maxCount(64).fireproof().rarity(Rarity.EPIC)))
    val SUPER_INGOT = registerItem("super_ingot",
        SuperIngot(FabricItemSettings().group(MiTech.itemGroup).maxCount(1).maxDamage(300).fireproof().rarity(Rarity.EPIC)))
    val SAMPLER = registerItem("sampler",
        Sampler(FabricItemSettings().group(MiTech.itemGroup).maxCount(1).maxDamage(1200).rarity(Rarity.UNCOMMON)))

    // Blocks

    val COLLECTOR = registerBlock("collector",
        Collector(FabricBlockSettings.of(Material.METAL).strength(4f).luminance(10).requiresTool()))
    val NEUTRON_BLOCK = registerBlock("neutron_block",
        Block(FabricBlockSettings.of(Material.METAL).strength(4f).luminance(5).requiresTool()))



    fun registerBlock(name: String, block: Block): Block{
        Registry.register(Registry.ITEM, Identifier(MiTech.MI, name),
            BlockItem(block, FabricItemSettings().group(MiTech.blockGroup)))
        return Registry.register(Registry.BLOCK, Identifier(MiTech.MI, name), block)
    }

    fun registerItem(name: String, item: Item) = Registry.register(Registry.ITEM, Identifier(MiTech.MI, name), item)
    fun init(){}
}
