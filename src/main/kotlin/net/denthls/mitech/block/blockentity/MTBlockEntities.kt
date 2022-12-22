package net.denthls.mitech.block.blockentity

import net.denthls.mitech.MiTech
import net.denthls.mitech.block.MTBlocks
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class MTBlockEntities<T : BlockEntity> {

    /*var CollectorBlockEntityType: BlockEntityType<CollectorBlockEntity>? = null
    val CollectorBlockEntityFactory = FabricBlockEntityTypeBuilder.Factory{pos, state ->
        CollectorBlockEntity(pos, state, CollectorBlockEntityType)
    }*/
    var CollectorBlockEntityType: BlockEntityType<T>? = null
    val CollectorBlockEntityFactory = FabricBlockEntityTypeBuilder.Factory { pos, state ->
        CollectorBlockEntity(
            pos,
            state,
            CollectorBlockEntityType
        )
    }

    fun init() {
        /*CollectorBlockEntityType = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            Identifier(MiTech.MI, "collector_block_entity"),
            FabricBlockEntityTypeBuilder.create(CollectorBlockEntityFactory, MTBlocks.COLLECTOR).build(null)
        )*/

        CollectorBlockEntityType = register("collector_block_entity", CollectorBlockEntityFactory, MTBlocks.COLLECTOR)

    }

    fun register(
        id: String,
        factory: FabricBlockEntityTypeBuilder.Factory<*>,
        block: Block
    ): BlockEntityType<BlockEntity>? {
        return Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            Identifier(MiTech.MI, id),
            FabricBlockEntityTypeBuilder.create(factory, block).build(null)
        )
    }
}