package net.denthls.mineralas.block

import net.denthls.mineralas.Mineralas
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object MnOres {
    val HEMATITE = register(
        "hematite", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    //deepslate
    val LIMONITE = register(
        "limonite", Block(
        FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    val MALACHITE = register(
        "malachite", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    //deepslate
    val AZURITE = register(
        "azurite", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    //deepslate
    val NATIVE_GOLD = register(
        "native_gold", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    //deepslate
    val ULTRABASITE = register(
        "ultrabasite", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    //deepslate
    val CINNABAR = register(
        "cinnabar", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    val QUARTZ = register(
        "quartz", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    val ANTIMONITE = register(
        "antimonite", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    val FOSSIL_COAL = register(
        "fossil_coal", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    //deepslate
    val ARGENTITE = register(
        "argentite", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    //deepslate
    val NEVYANSKITE = register(
        "nevyanskite", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    val CASSITERITE = register(
        "cassiterite", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    val PENTLANDITE = register(
        "pentlandite", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    val HALITE = register(
        "halite", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    //deepslate
    val WOLFRAMITE = register(
        "wolframite", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    //deepslate
    val URANINITE = register(
        "uraninite", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    val BAUXITE = register(
        "bauxite", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    val GALENA = register(
        "galena", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )
    //deepslate
    val MOZANITE = register(
        "mozanite", Block(
            FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f))
    )

    private fun register(name: String, block: Block): Block {
        Registry.register(
            Registry.ITEM, Identifier(Mineralas.MI, name),
            BlockItem(block, FabricItemSettings().group(Mineralas.oresGroup))
        )
        return Registry.register(Registry.BLOCK, Identifier(Mineralas.MI, name), block)
    }
}