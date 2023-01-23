package net.denthls.mineralas.block.custom

import net.minecraft.block.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView

class SampleBlock(settings: Settings) : Block(settings) {

    @Deprecated("Deprecated in Java")
    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        val offset = state.getModelOffset(world, pos)
        return VoxelShapes.cuboid(0.2, 0.0, 0.2, 0.8, 0.2, 0.8).offset(offset.x, offset.y, offset.z)
    }

}