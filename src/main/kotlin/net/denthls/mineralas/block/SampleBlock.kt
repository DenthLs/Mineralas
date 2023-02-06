package net.denthls.mineralas.block

import net.minecraft.block.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.state.StateManager
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess


class SampleBlock(settings: Settings) : Block(settings) {

    @Deprecated("Deprecated in Java")
    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        if (!world.isClient && player is ServerPlayerEntity) {
            player.swingHand(hand, true)
            world.breakBlock(pos, false)
        }
        return super.onUse(state, world, pos, player, hand, hit)
    }

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

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        return super.getPlacementState(ctx)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
    }

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)",
            "net.minecraft.block.Block"
        )
    )
    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        /*if (neighborState.fluidState == Fluids.FLOWING_WATER.defaultState ||
            neighborState.fluidState == Fluids.WATER.defaultState) {
            world.breakBlock(pos, false)
        }*/
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }
}