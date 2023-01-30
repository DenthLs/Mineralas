package net.denthls.mineralas.world.feature

import com.mojang.serialization.Codec
import net.denthls.mineralas.world.GenerateVein
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.structure.rule.RuleTest
import net.minecraft.tag.BlockTags
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreConfiguredFeatures
import net.minecraft.world.gen.feature.OreFeatureConfig
import net.minecraft.world.gen.feature.util.FeatureContext
import kotlin.random.Random


open class MnOreSampleFeature(configCodec: Codec<MnFeatureConfig>) : Feature<MnFeatureConfig>(configCodec) {
    override fun generate(context: FeatureContext<MnFeatureConfig>): Boolean {

        val world: StructureWorldAccess = context.world
        val origin: BlockPos = context.origin
        val config: MnFeatureConfig = context.config

        val blockId: Identifier = config.blockId
        val oreBlockId: Identifier = config.oreBlockId
        val size: Int = config.size
        val random: java.util.Random = java.util.Random(8)
        val height: String = config.height

        val blockState: BlockState = Registry.BLOCK.get(blockId).defaultState
        var blockPos = surface(origin)
        var currentBlockState: BlockState
        var testPos: BlockPos
        var count = 0
        val maxCountSamples = 7
        val target = createTarget(height, oreBlockId)

        for (y in 50..world.topY) {
            currentBlockState = world.getBlockState(blockPos)
            blockPos = blockPos.up()
            GenerateVein.generateOre(blockPos, world, size, target, random, height)
            if (surfaceContains(currentBlockState)) {
                if (isAirUpToThree(world, blockPos)) {
                    testPos = blockPos
                    testPos = testPos.add(4, 0, 4)
                    for (x in 1..9) {
                        for (z in 1..9) {
                            if (surfaceContains(world.getBlockState(testPos.down()))) {
                                if (isAirUpToThree(world, testPos) &&
                                    surfaceContains(world.getBlockState(testPos.up(airUpToThree(world, testPos) - 1)))
                                ) {
                                    if (Random.nextInt(1, 100) > 94) {
                                        world.setBlockState(
                                            testPos.up(airUpToThree(world, testPos)),
                                            blockState,
                                            3
                                        )
                                        count++
                                        if (count == maxCountSamples) return true
                                    }
                                }
                            } else {
                                if (isSurfaceDownToThree(world, testPos.down())) {
                                    if (airContains(
                                            world.getBlockState(
                                                testPos.up(
                                                    airUpToThree(
                                                        world,
                                                        testPos
                                                    ) + 1
                                                )
                                            )
                                        )
                                    ) {
                                        if (Random.nextInt(1, 100) > 94) {
                                            world.setBlockState(
                                                testPos.down(surfaceDownToThree(world, testPos)),
                                                blockState,
                                                3
                                            )
                                            count++
                                            if (count == maxCountSamples) return true
                                        }
                                    }
                                }
                            }
                            testPos = testPos.add(0, 0, -1)
                        }
                        testPos = testPos.add(0, 0, 9)
                        testPos = testPos.add(-1, 0, 0)
                    }
                    return true
                }
            }
        }
        return false
    }

    private fun isSurfaceDownToThree(world: StructureWorldAccess, pos: BlockPos): Boolean {
        var blockPos = pos.down()
        var currentBlockState: BlockState
        for (i in 0..3) {
            currentBlockState = world.getBlockState(blockPos)
            if (surfaceContains(currentBlockState)) return true
            blockPos = blockPos.down()
        }
        return false
    }

    private fun surfaceDownToThree(world: StructureWorldAccess, pos: BlockPos): Int {
        var blockPos = pos.down()
        var currentBlockState: BlockState
        for (i in 0..3) {
            currentBlockState = world.getBlockState(blockPos)
            if (surfaceContains(currentBlockState)) return i
            blockPos = blockPos.down()
        }
        return 0
    }

    private fun isAirUpToThree(world: StructureWorldAccess, pos: BlockPos): Boolean {
        var blockPos = pos
        var currentBlockState: BlockState
        for (i in 0..3) {
            currentBlockState = world.getBlockState(blockPos)
            if (airContains(currentBlockState)) return true
            blockPos = blockPos.up()
        }
        return false
    }

    private fun airUpToThree(world: StructureWorldAccess, pos: BlockPos): Int {
        var blockPos = pos
        var currentBlockState: BlockState
        for (i in 0..3) {
            currentBlockState = world.getBlockState(blockPos)
            if (airContains(currentBlockState)) return i
            blockPos = blockPos.up()
        }
        return 0
    }

    private val tags: List<TagKey<Block>> =
        listOf(BlockTags.DIRT, BlockTags.STONE_ORE_REPLACEABLES, BlockTags.SAND, BlockTags.TERRACOTTA)

    private fun surfaceContains(blockState: BlockState): Boolean {
        for (i in 0..3) {
            if (blockState.isIn(tags[i])) return true
        }
        if (blockState.isOf(Blocks.SNOW_BLOCK)) return true
        return false
    }

    private val airBlocks: List<Block> = listOf(Blocks.SNOW, Blocks.AIR, Blocks.GRASS)
    private fun airContains(blockState: BlockState): Boolean {
        for (i in 0..2) {
            if (blockState.isOf(airBlocks[i])) return true
        }
        return false
    }

    private fun surface(blockPos: BlockPos): BlockPos {
        val x = blockPos.x
        val z = blockPos.z
        return BlockPos(x, 50, z)
    }

    private fun createTarget(height: String, blockId: Identifier): List<OreFeatureConfig.Target> {
        val blockState: BlockState = Registry.BLOCK.get(blockId).defaultState
        val ruleTest: RuleTest = when (height) {
            "stone" -> OreConfiguredFeatures.STONE_ORE_REPLACEABLES
            "deepslate" -> OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES
            else -> {
                OreConfiguredFeatures.STONE_ORE_REPLACEABLES
            }
        }
        return listOf(OreFeatureConfig.createTarget(ruleTest, blockState))
    }

}