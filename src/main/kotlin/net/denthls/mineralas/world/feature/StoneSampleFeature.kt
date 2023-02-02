package net.denthls.mineralas.world.feature

import com.mojang.serialization.Codec
import net.denthls.mineralas.Mineralas.logger
import net.denthls.mineralas.world.feature.featureConfigs.SampleFeatureConfig
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.tag.BlockTags
import net.minecraft.tag.TagKey
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
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
        val originY = origin.y
        var blockPos: BlockPos
        (chunkPos.startX..chunkPos.endX).forEach { x ->
            (chunkPos.startZ..chunkPos.endZ).forEach { z ->
                blockPos = BlockPos(x, originY, z)
                if (surfaceContains(world.getBlockState(blockPos)) && world.getBlockState(blockPos.up()).isAir &&
                    Random.nextInt(0, 100) > rarity
                ) {
                    world.setBlockState(blockPos.up(), Registry.BLOCK.get(id).defaultState, 3)
                    logger.info(blockPos.up().toString())
                } else {
                    (0..8).forEach y@{ y ->
                        if (surfaceContains(world.getBlockState(blockPos.up(y))) && world.getBlockState(blockPos.up(y + 1)).isAir &&
                            Random.nextInt(0, 100) > rarity
                        ) {
                            world.setBlockState(blockPos.up(y + 1), Registry.BLOCK.get(id).defaultState, 3)
                            logger.info(blockPos.up(y + 1).toString())
                            return@y
                        } else if (surfaceContains(world.getBlockState(blockPos.down(y))) && world.getBlockState(
                                blockPos.down(y - 1)
                            ).isAir &&
                            Random.nextInt(0, 100) > rarity
                        ) {
                            world.setBlockState(blockPos.down(y - 1), Registry.BLOCK.get(id).defaultState, 3)
                            logger.info(blockPos.up(y - 1).toString())
                            return@y
                        }
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
}