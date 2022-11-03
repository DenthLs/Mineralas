package net.denthls.mitech.block

import net.denthls.mitech.block.blockentity.CollectorBlockEntity
import net.denthls.mitech.item.ModItems
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.HorizontalFacingBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos

class Collector(settings: Settings) : HorizontalFacingBlock(settings), BlockEntityProvider {

    var LIT: Boolean = false

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(Properties.HORIZONTAL_FACING)
        builder.add(Properties.LIT)
        super.appendProperties(builder)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        return super.getPlacementState(ctx)?.with(Properties.HORIZONTAL_FACING, ctx.playerFacing.opposite)
    }

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
        return CollectorBlockEntity(pos, state)
    }

}
