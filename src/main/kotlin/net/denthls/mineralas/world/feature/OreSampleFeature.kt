package net.denthls.mineralas.world.feature

import com.mojang.serialization.Codec
import net.denthls.mineralas.Mineralas
import net.denthls.mineralas.Mineralas.random
import net.denthls.mineralas.Mineralas.samples
import net.denthls.mineralas.registry.SamplesRegistry.samplesId
import net.denthls.mineralas.world.GenerateDeposit
import net.denthls.mineralas.world.feature.featureConfigs.MnFeatureConfig
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.tag.BiomeTags
import net.minecraft.tag.BlockTags
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.Heightmap
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreConfiguredFeatures
import net.minecraft.world.gen.feature.OreFeatureConfig
import net.minecraft.world.gen.feature.util.FeatureContext


open class OreSampleFeature(configCodec: Codec<MnFeatureConfig>) : Feature<MnFeatureConfig>(configCodec) {
    override fun generate(context: FeatureContext<MnFeatureConfig>): Boolean {
        if (context.world.getBiome(context.origin).isIn(BiomeTags.IS_OCEAN) ||
            context.world.getBiome(context.origin).isIn(BiomeTags.IS_RIVER)
        ) return false
        val world: StructureWorldAccess = context.world
        val config: MnFeatureConfig = context.config
        val chunkPos = ChunkPos(context.origin)
        val blockState: BlockState = Registry.BLOCK.get(config.sampleId).defaultState
        val height = if (random(0.5f)) "stone" else "deepslate"
        var orePath = config.oreId.path
        val oreNamespace = config.oreId.namespace
        if (height == "deepslate") orePath = "deepslate_$orePath"
        val oreId = Identifier(oreNamespace, orePath)
        (chunkPos.startX + 4..chunkPos.endX - 4).forEach { x ->
            (chunkPos.startZ + 4..chunkPos.endZ - 4).forEach { z ->
                val y = world.getTopY(Heightmap.Type.WORLD_SURFACE, x, z)
                val blockPos = BlockPos(x, y, z)
                samplesId.minus("stone_sample").forEach {
                    val block = Registry.BLOCK.get(Identifier(Mineralas.MI, it))
                    if (block.defaultState.equals(world.getBlockState(blockPos.down()))) return false
                    else if (world.getBlockState(blockPos.down()).isIn(BlockTags.LEAVES)) {
                        (1..12).forEach i@{ i ->
                            if (block.defaultState.equals(world.getBlockState(blockPos.down(i)))) return false
                            else if (world.getBlockState(blockPos.down(i)).isIn(BlockTags.LOGS)) return@i
                        }
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
        var count = 0
        (chunkPos.startX + 4..chunkPos.endX - 4).forEach { x ->
            (chunkPos.startZ + 4..chunkPos.endZ - 4).forEach { z ->
                val y = world.getTopY(Heightmap.Type.WORLD_SURFACE, x, z)
                val blockPos = BlockPos(x, y, z)
                if (surfaceContains(world.getBlockState(blockPos.down())) &&
                    random(0.2f) &&
                    blockPos.getNeighbor(world)
                ) {
                    world.setBlockState(blockPos, blockState, 3)
                    if (count > 8) return true else ++count
                } else if (world.getBlockState(blockPos.down()).isIn(BlockTags.LEAVES) &&
                    random(0.2f)
                ) {
                    (1..12).forEach i@{ i ->
                        if (world.getBlockState(blockPos.down(i)).isAir &&
                            surfaceContains(world.getBlockState(blockPos.down(i + 1)))
                        ) {
                            if (blockPos.down(i).getNeighbor(world)) {
                                world.setBlockState(blockPos.down(i), blockState, 3)
                                if (count > 8) return true else ++count
                                return@i
                            }
                        }
                    }
                } else if (world.getBlockState(blockPos.down()).equals(Blocks.GRASS.defaultState) &&
                    random(0.2f)
                ) {
                    if (blockPos.down().getNeighbor(world)) {
                        world.setBlockState(blockPos.down(), blockState, 3)
                        if (count > 8) return true else ++count
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