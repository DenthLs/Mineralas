package net.denthls.mitech.block.blockentity

import net.minecraft.block.BlockState
import net.minecraft.block.InventoryProvider
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.WorldAccess


class CollectorBlockEntity(pos: BlockPos?, state: BlockState?, type: BlockEntityType<*>?) :
    BlockEntity(type, pos, state),
    NamedScreenHandlerFactory, InventoryProvider {

    val inventory: DefaultedList<ItemStack> = DefaultedList.ofSize(3, ItemStack.EMPTY)

    override fun createMenu(syncId: Int, inv: PlayerInventory?, player: PlayerEntity?): ScreenHandler? {
        return null
    }

    override fun getDisplayName(): Text {
        TODO("Not yet implemented")
    }

    override fun getInventory(state: BlockState?, world: WorldAccess?, pos: BlockPos?): SidedInventory {
        TODO("Not yet implemented")
    }

    companion object {
        fun tick(world: World?, pos: BlockPos?, state: BlockState?, entity: CollectorBlockEntity) {

        }
    }
}