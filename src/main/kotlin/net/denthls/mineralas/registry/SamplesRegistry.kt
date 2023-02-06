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

    val IRON_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("iron")
    val COPPER_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("copper")
    val COAL_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("coal")
    val LAPIS_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("lapis")
    val GOLD_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("gold")
    val REDSTONE_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("redstone")
    val DIAMOND_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("diamond")
    val EMERALD_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("emerald")
    val ANTIMONY_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("antimony")
    val BAUXITE_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("bauxite")
    val IRIDIUM_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("iridium")
    val LEAD_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("lead")
    val MOZANITE_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("mozanite")
    val NICKEL_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("nickel")
    val SALT_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("salt")
    val TIN_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("tin")
    val TUNGSTEN_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("tungsten")
    val URANIUM_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("uranium")
    val RUBY_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("ruby")
    val SAPPHIRE_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("sapphire")
    val SILVER_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("silver")
    val QUARTZ_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("quartz")
    val NIKOLITE_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("nikolite")

    val STONE_SAMPLE = SampleBlock(
        FabricBlockSettings.of(Material.STONE).breakInstantly().sounds(BlockSoundGroup.STONE).noCollision()
    ).create("stone")

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
        SAMPLE[this] = Identifier(Mineralas.MI, name + "_sample")
        return this
    }

    val samples = setOf(
        IRON_SAMPLE, COPPER_SAMPLE, COAL_SAMPLE, LAPIS_SAMPLE, GOLD_SAMPLE, REDSTONE_SAMPLE,
        DIAMOND_SAMPLE, EMERALD_SAMPLE, ANTIMONY_SAMPLE, BAUXITE_SAMPLE, IRIDIUM_SAMPLE, LEAD_SAMPLE,
        MOZANITE_SAMPLE, NICKEL_SAMPLE, SALT_SAMPLE, TIN_SAMPLE, TUNGSTEN_SAMPLE,
        URANIUM_SAMPLE, RUBY_SAMPLE, SAPPHIRE_SAMPLE, SILVER_SAMPLE, QUARTZ_SAMPLE, STONE_SAMPLE, NIKOLITE_SAMPLE
    )

}
