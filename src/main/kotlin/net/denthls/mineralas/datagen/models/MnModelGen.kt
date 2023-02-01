package net.denthls.mineralas.datagen.models

import net.denthls.mineralas.Mineralas
import net.denthls.mineralas.block.MnOres
import net.denthls.mineralas.block.MnSamples
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.*
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import java.util.*

class MnModelGen(dataGenerator: FabricDataGenerator) : FabricModelProvider(dataGenerator) {
    private val ore: TextureKey = TextureKey.of("ore")
    private val _ore: TextureKey = TextureKey.of("_ore")
    private val stone: TextureKey = TextureKey.of("stone")
    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator) {
        for (element in l) {
            val id = Registry.BLOCK.getId(element).path
            blockStateModelGenerator.registerSimpleCubeAll(element)
            blockStateModelGenerator.registerParentedItemModel(element, Identifier(Mineralas.MI, "block/$id"))
        }
        for (element in s) {
            val i = Registry.BLOCK.getId(element).path
            registerSample(blockStateModelGenerator, i)
        }

    }
    override fun generateItemModels(itemModelGenerator: ItemModelGenerator) {
        // override nothing
    }
    private fun registerSample(blockStateModelGenerator: BlockStateModelGenerator, id: String){
        val block = Registry.BLOCK.get(Identifier(Mineralas.MI, id))
        val m = id.dropLast(7)
        blockStateModelGenerator.registerSingleton(block, TextureMap().put(TextureKey.PARTICLE, Identifier("minecraft:block/stone")).put(
            ore,
            Identifier(Mineralas.MI, "block/$m")
        )
            .put(_ore, Identifier(Mineralas.MI, "block/$m")).put(
                stone,
                Identifier("minecraft:block/stone")
            ), Model(Optional.of(Identifier(Mineralas.MI, "block/sample")), Optional.empty(), TextureKey.PARTICLE, ore,
                _ore, stone)
        )
    }
    private val l = listOf(MnOres.HEMATITE, MnOres.LIMONITE, MnOres.MALACHITE, MnOres.AZURITE,
        MnOres.NATIVE_GOLD, MnOres.ULTRABASITE, MnOres.CINNABAR, MnOres.QUARTZ,
        MnOres.ANTIMONITE, MnOres.FOSSIL_COAL, MnOres.ARGENTITE, MnOres.NEVYANSKITE,
        MnOres.CASSITERITE, MnOres.PENTLANDITE, MnOres.HALITE, MnOres.WOLFRAMITE,
        MnOres.URANINITE, MnOres.BAUXITE, MnOres.GALENA, MnOres.MOZANITE)
    val s = listOf(MnSamples.BAUXITE_SAMPLE)
}