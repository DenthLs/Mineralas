package net.denthls.mineralas.block

import net.denthls.mineralas.Mineralas
import net.denthls.mineralas.block.custom.SampleBlock
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object MnSamples {

    val HEMATITE_SAMPLE = register("hematite_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val LIMONITE_SAMPLE = register("limonite_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val MALACHITE_SAMPLE = register("malachite_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val AZURITE_SAMPLE = register("azurite_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val NATIVE_GOLD_SAMPLE = register("native_gold_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val ULTRABASITE_SAMPLE = register("ultrabasite_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val CINNABAR_SAMPLE = register("cinnabar_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val QUARTZ_SAMPLE = register("quartz_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val ANTIMONITE_SAMPLE = register("antimonite_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val FOSSIL_COAL_SAMPLE = register("fossil_coal_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val ARGENTITE_SAMPLE = register("argentite_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val NEVYANSKITE_SAMPLE = register("nevyanskite_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val CASSITERITE_SAMPLE = register("cassiterite_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val PENTLANDITE_SAMPLE = register("pentlandite_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val HALITE_SAMPLE = register("halite_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val URANINITE_SAMPLE = register("uraninite_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val BAUXITE_SAMPLE = register("bauxite_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val GALENA_SAMPLE = register("galena_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))
    val MOZANITE_SAMPLE = register("mozanite_sample", SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ))

    private fun register(name: String, block: Block): Block {
        Registry.register(
            Registry.ITEM, Identifier(Mineralas.MI, name),
            BlockItem(block, FabricItemSettings().group(Mineralas.samplesGroup))
        )
        return Registry.register(Registry.BLOCK, Identifier(Mineralas.MI, name), block)
    }

}