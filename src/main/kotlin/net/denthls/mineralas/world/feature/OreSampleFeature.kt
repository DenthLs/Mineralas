package net.denthls.mineralas.world.feature

import com.mojang.serialization.Codec
import net.denthls.mineralas.registry.SamplesRegistry.STONE_SAMPLE
import net.denthls.mineralas.registry.SamplesRegistry.samples
import net.denthls.mineralas.world.GenerateDeposit
import net.denthls.mineralas.world.feature.featureConfigs.MnFeatureConfig
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.tag.BlockTags
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreConfiguredFeatures
import net.minecraft.world.gen.feature.OreFeatureConfig
import net.minecraft.world.gen.feature.util.FeatureContext
import kotlin.random.Random


open class OreSampleFeature(configCodec: Codec<MnFeatureConfig>) : Feature<MnFeatureConfig>(configCodec) {
    override fun generate(context: FeatureContext<MnFeatureConfig>): Boolean {

        val world: StructureWorldAccess = context.world
        val config: MnFeatureConfig = context.config

        val blockState: BlockState = Registry.BLOCK.get(config.sampleId).defaultState
        val chunkPos = ChunkPos(context.origin)
        val height = if (Random.nextFloat() > 0.5) "stone" else "deepslate"
        var orePath = config.oreId.path
        val oreNamespace = config.oreId.namespace
        if (height == "deepslate") orePath = "deepslate_$orePath"
        val oreId = Identifier(oreNamespace, orePath)
        (chunkPos.startX..chunkPos.endX).forEach { x ->
            (chunkPos.startZ..chunkPos.endZ).forEach { z ->
                (50..160).forEach y@{ y ->
                    val blockPos = BlockPos(x, y, z)
                    samples.minus(STONE_SAMPLE).forEach {
                        if (it.defaultState.equals(world.getBlockState(blockPos))) return false
                    }
                }
            }
        }
        var count = 0
        (chunkPos.startX..chunkPos.endX).forEach { x ->
            (chunkPos.startZ..chunkPos.endZ).forEach { z ->
                (50..160).forEach y@{ y ->
                    val blockPos = BlockPos(x, y, z)
                    if (surfaceContains(world.getBlockState(blockPos)) && world.getBlockState(blockPos.up()).isAir
                        && world.getBlockState(blockPos.up(2)).isAir
                        && Random.nextInt(1, 100) > 94
                        && blockPos.up().getNeighbor(world)
                    ) {
                        world.setBlockState(blockPos.up(), blockState, 3)
                        if (count > 7) return true else ++count
                        return@y
                    }
                }
            }
        }
        GenerateDeposit.generateOre(
            BlockPos(ChunkPos(context.origin).centerX, 80, ChunkPos(context.origin).centerZ),
            world,
            config.size,
            createTarget(height, oreId),
            height
        )
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

    private fun createTarget(height: String, blockId: Identifier): List<OreFeatureConfig.Target> =
        listOf(
            OreFeatureConfig.createTarget(
                when (height) {
                    "stone" -> OreConfiguredFeatures.STONE_ORE_REPLACEABLES
                    "deepslate" -> OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES
                    else -> OreConfiguredFeatures.STONE_ORE_REPLACEABLES
                }, Registry.BLOCK.get(blockId).defaultState
            )
        )

    private fun BlockPos.getNeighbor(world: StructureWorldAccess): Boolean {
        (-1..1).forEach { y ->
            (-1..1).forEach { x ->
                (-1..1).forEach { z ->
                    if (samples.contains(world.getBlockState(this.add(x, y, z)).block)) return false
                }
            }
        }
        return true
    }
}