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
    override fun generate(context: FeatureContext<OreFeatureConfig>): Boolean {
//        Actually not optimized, but now IDK how to improve it
        val world: StructureWorldAccess = context.world
        val origin: BlockPos = context.origin
        val chunkPos = ChunkPos(origin)
        for (x in chunkPos.startX..chunkPos.endX){
            for (z in chunkPos.startZ..chunkPos.endZ){
                for (y in world.bottomY..64){
                    val blockState = world.getBlockState(BlockPos(x, y, z))
                    if (listCopper.contains(blockState)){
                        world.setBlockState(BlockPos(x,y,z), Blocks.STONE.defaultState, 0x10)
                    }
                    if (listIron.contains(blockState)){
                        world.setBlockState(BlockPos(x,y,z), Blocks.DEEPSLATE.defaultState, 0x10)
                    }
                }
            }
        }
        return true
    }
    private val listCopper = listOf(Blocks.COPPER_ORE.defaultState, Blocks.RAW_COPPER_BLOCK.defaultState)
    private val listIron = listOf(Blocks.DEEPSLATE_IRON_ORE.defaultState, Blocks.RAW_IRON_BLOCK.defaultState)
}