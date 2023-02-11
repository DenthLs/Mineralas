package net.denthls.mineralas.world.feature

import com.mojang.serialization.Codec
import net.denthls.mineralas.Mineralas
import net.denthls.mineralas.world.feature.featureConfigs.SampleFeatureConfig
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.tag.BiomeTags
import net.minecraft.tag.BlockTags
import net.minecraft.tag.TagKey
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.Heightmap
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext

class StoneSampleFeature(configCodec: Codec<SampleFeatureConfig>) : Feature<SampleFeatureConfig>(configCodec) {
    override fun generate(context: FeatureContext<SampleFeatureConfig>): Boolean {
        if (context.world.getBiome(context.origin).isIn(BiomeTags.IS_OCEAN) ||
            context.world.getBiome(context.origin).isIn(BiomeTags.IS_RIVER)
        ) return false
        val world = context.world
        val origin = context.origin
        val config = context.config
        val id = config.sampleId
        val rarity = config.rarity
        val chunkPos = world.getChunk(origin).pos
        val blockState = Registry.BLOCK.get(id).defaultState
        (chunkPos.startX..chunkPos.endX).forEach { x ->
            (chunkPos.startZ..chunkPos.endZ).forEach { z ->
                val y = world.getTopY(Heightmap.Type.WORLD_SURFACE, x, z)
                val blockPos = BlockPos(x, y, z)
                if (surfaceContains(world.getBlockState(blockPos.down())) &&
                    Mineralas.random(rarity) &&
                    blockPos.getNeighbor(world)
                ) {
                    world.setBlockState(blockPos, blockState, 3)
                } else if (world.getBlockState(blockPos.down()).isIn(BlockTags.LEAVES) &&
                    Mineralas.random(rarity)
                ) {
                    (1..12).forEach i@{ i ->
                        if (world.getBlockState(blockPos.down(i)).isAir &&
                            surfaceContains(world.getBlockState(blockPos.down(i + 1)))
                        ) {
                            if (blockPos.down(i).getNeighbor(world)) {
                                world.setBlockState(blockPos.down(i), blockState, 3)
                                return@i
                            }
                        }
                    }
                } else if (world.getBlockState(blockPos.down()).equals(Blocks.GRASS.defaultState) &&
                    Mineralas.random(rarity)
                ) {
                    if (blockPos.down().getNeighbor(world)) {
                        world.setBlockState(blockPos.down(), blockState, 3)
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
                    if (Mineralas.samples.contains(world.getBlockState(this.add(x, y, z)).block)) return false
                }
            }
        }
        return true
    }
}