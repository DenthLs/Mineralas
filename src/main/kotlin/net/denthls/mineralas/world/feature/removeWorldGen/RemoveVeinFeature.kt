package net.denthls.mineralas.world.feature.removeWorldGen

import com.mojang.serialization.Codec
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreFeatureConfig
import net.minecraft.world.gen.feature.util.FeatureContext

class RemoveVeinFeature(configCodec: Codec<OreFeatureConfig>?) : Feature<OreFeatureConfig>(configCodec) {

    private val listCopper = listOf(Blocks.COPPER_ORE.defaultState, Blocks.RAW_COPPER_BLOCK.defaultState)
    private val listIron = listOf(Blocks.DEEPSLATE_IRON_ORE.defaultState, Blocks.RAW_IRON_BLOCK.defaultState)

    override fun generate(context: FeatureContext<OreFeatureConfig>): Boolean {
        val world: StructureWorldAccess = context.world
        val chunkPos = ChunkPos(context.origin)
        chunkPos.startX.rangeTo(chunkPos.endX).forEach { x ->
            chunkPos.startZ.rangeTo(chunkPos.endZ).forEach { z ->
                world.bottomY.rangeTo(64).forEach { y ->
                    world.getBlockState(BlockPos(x, y, z)).apply {
                        if (listCopper.contains(this))
                            world.setBlockState(BlockPos(x, y, z), Blocks.STONE.defaultState, 0x10)
                        if (listIron.contains(this))
                            world.setBlockState(BlockPos(x, y, z), Blocks.DEEPSLATE.defaultState, 0x10)
                    }
                }
            }
        }
        return true
    }
}