package net.denthls.mitech.block

import net.denthls.mitech.block.blockentity.CollectorBlockEntity
import net.denthls.mitech.block.blockentity.MTBlockEntities.CollectorBlockEntityType
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Collector(
    private val factory: (FabricBlockEntityTypeBuilder.Factory<CollectorBlockEntity>)?,
    settings: Settings
) : BlockWithEntity(settings),
    BlockEntityProvider {

    var LIT: Boolean = false

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(Properties.HORIZONTAL_FACING)
        builder.add(Properties.LIT)
        super.appendProperties(builder)
    }

    override fun onUse(
        state: BlockState?,
        world: World?,
        pos: BlockPos?,
        player: PlayerEntity?,
        hand: Hand?,
        hit: BlockHitResult?
    ): ActionResult {
        return super.onUse(state, world, pos, player, hand, hit)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? =
        super.getPlacementState(ctx)?.with(Properties.HORIZONTAL_FACING, ctx.playerFacing.opposite)

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity =
        CollectorBlockEntity(pos, state, CollectorBlockEntityType)

    override fun getRenderType(state: BlockState?): BlockRenderType = BlockRenderType.MODEL

    override fun <T : BlockEntity?> getTicker(
        world: World?,
        state: BlockState?,
        type: BlockEntityType<T>?
    ): BlockEntityTicker<T>? {
        return checkType(type, CollectorBlockEntityType, CollectorBlockEntity::tick)
    }

}
