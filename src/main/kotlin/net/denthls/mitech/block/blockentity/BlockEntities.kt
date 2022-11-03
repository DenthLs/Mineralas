package net.denthls.mitech.block.blockentity

import net.denthls.mitech.MiTech
import net.denthls.mitech.item.ModItems
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object BlockEntities {
    lateinit var COLLECTOR: BlockEntityType<BlockEntity>
    fun registerBlockEntities(){
         COLLECTOR = Registry.register(Registry.BLOCK_ENTITY_TYPE,
            Identifier(MiTech.MI, "collector"), FabricBlockEntityTypeBuilder()
         )
         )
    }

}