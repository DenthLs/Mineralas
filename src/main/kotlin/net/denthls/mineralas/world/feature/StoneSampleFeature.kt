package net.denthls.mineralas.world.feature

import com.mojang.serialization.Codec
import net.denthls.mineralas.registry.SamplesRegistry
import net.denthls.mineralas.world.feature.featureConfigs.SampleFeatureConfig
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.tag.BlockTags
import net.minecraft.tag.TagKey
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext
import kotlin.random.Random

class StoneSampleFeature(configCodec: Codec<SampleFeatureConfig>) : Feature<SampleFeatureConfig>(configCodec) {
    override fun generate(context: FeatureContext<SampleFeatureConfig>): Boolean {
        val world = context.world
        val origin = context.origin
        val config = context.config

        val id = config.sampleId
        val rarity = config.rarity
        val chunkPos = world.getChunk(origin).pos
        (chunkPos.startX..chunkPos.endX).forEach { x ->
            (chunkPos.startZ..chunkPos.endZ).forEach { z ->
                (50..160).forEach y@{ y ->
                    val blockPos = BlockPos(x, y, z)
                    if (surfaceContains(world.getBlockState(blockPos)) && world.getBlockState(blockPos.up()).isAir
                        && world.getBlockState(blockPos.up(2)).isAir
                        && world.getBlockState(blockPos.up(10)).isAir
                        && Random.nextInt(1, 100) > rarity
                        && blockPos.up().getNeighbor(world)
                    ) {
                        world.setBlockState(blockPos.up(), Registry.BLOCK.get(id).defaultState, 3)
                        return@y
                    }
                }
            }
        }
        return true
    }

    private val tags: List<TagKey<Block>> =
        listOf(BlockTags.DIRT, BlockTags.STONE_ORE_REPLACEABLES, BlockTags.SAND, BlockTags.TERRACOTTA)

    private fun surfaceContains(blockState: BlockState): Boolean {
        (0..3).forEach { i ->
            if (blockState.isIn(tags[i])) return true
        }
        if (blockState.isOf(Blocks.SNOW_BLOCK)) return true
        return false
    }

    private fun BlockPos.getNeighbor(world: StructureWorldAccess): Boolean {
        (-1..1).forEach { y ->
            (-1..1).forEach { x ->
                (-1..1).forEach { z ->
                    if (SamplesRegistry.samples.contains(world.getBlockState(this.add(x, y, z)).block)) return false
                }
            }
        }
        return true
    }
}