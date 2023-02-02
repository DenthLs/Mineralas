package net.denthls.mineralas.registry

import net.denthls.mineralas.Mineralas
import net.denthls.mineralas.Mineralas.samplesGroup
import net.denthls.mineralas.block.SampleBlock
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object SamplesRegistry {

    private val SAMPLE: MutableMap<SampleBlock, Identifier> = LinkedHashMap()

    val ANTIMONITE_SAMPLE = SampleBlock(FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)).create("antimonite_sample")
    val ARGENTITE_SAMPLE =  SampleBlock(FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)).create("argentite_sample")
    val AZURITE_SAMPLE = SampleBlock(FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)).create("azurite_sample")
    val BAUXITE_SAMPLE = SampleBlock(FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)).create("bauxite_sample")
    val CASSITERITE_SAMPLE = SampleBlock(FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)).create("cassiterite_sample")
    val CINNABAR_SAMPLE = SampleBlock(FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)).create("cinnabar_sample")
    val FOSSIL_COAL_SAMPLE = SampleBlock(FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)).create("fossil_coal_sample")
    val GALENA_SAMPLE = SampleBlock(FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)).create("galena_sample")
    val HALITE_SAMPLE = SampleBlock(FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)).create("halite_sample")
    val HEMATITE_SAMPLE = SampleBlock(FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)).create("hematite_sample")
    val LIMONITE_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ).create("limonite_sample")
    val MALACHITE_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ).create("malachite_sample")
    val MOZANITE_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ).create("mozanite_sample")
    val NATIVE_GOLD_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ).create("native_gold_sample")
    val NEVYANSKITE_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ).create("nevyanskite_sample")
    val PENTLANDITE_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ).create("pentlandite_sample")
    val QUARTZ_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ).create("quartz_sample")
    val ULTRABASITE_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ).create("ultrabasite_sample")
    val URANINITE_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ).create("uraninite_sample")

    val STONE_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE)
    ).create("stone_sample")

    init {
        SAMPLE.keys.forEach {
            Registry.register(
                Registry.ITEM, SAMPLE[it],
                BlockItem(it, FabricItemSettings().group(samplesGroup))
            )
            Registry.register(Registry.BLOCK, SAMPLE[it], it)
        }
    }

    private fun SampleBlock.create(name: String): SampleBlock {
        SAMPLE[this] = Identifier(Mineralas.MI, name)
        return this
    }
}