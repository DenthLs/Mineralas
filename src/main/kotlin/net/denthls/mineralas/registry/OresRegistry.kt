package net.denthls.mineralas.registry

import net.denthls.mineralas.Mineralas
import net.denthls.mineralas.Mineralas.oresGroup
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object OresRegistry {

    private val ORES: MutableMap<Block, Identifier> = LinkedHashMap()

    val ANTIMONITE = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("antimonite")
    val BAUXITE = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("bauxite")
    val CASSITERITE = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("cassiterite")
    val FOSSIL_COAL = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("fossil_coal")
    val GALENA = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("galena")
    val HALITE = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("halite")
    val HEMATITE = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("hematite")
    val PENTLANDITE = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("pentlandite")
    val QUARTZ = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("quartz")

    /*   Deepslate   */

    val ARGENTITE = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("argentite")
    val AZURITE = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("azurite")
    val CINNABAR = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("cinnabar")
    val LIMONITE = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("limonite")
    val MALACHITE = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("malachite")
    val MOZANITE = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("mozanite")
    val NATIVE_GOLD = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("native_gold")
    val NEVYANSKITE = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("nevyanskite")
    val ULTRABASITE = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("ultrabasite")
    val URANINITE = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("uraninite")
    val WOLFRAMITE = Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(4f)).create("wolframite")

    init {
        ORES.keys.forEach {
            Registry.register(Registry.BLOCK, ORES[it], it)
            Registry.register(
                Registry.ITEM, ORES[it],
                BlockItem(it, FabricItemSettings().group(oresGroup))
            )
        }
    }

    private fun Block.create(name: String): Block {
        ORES[this] = Identifier(Mineralas.MI, name)
        return this
    }
}