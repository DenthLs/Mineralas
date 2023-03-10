package net.denthls.mineralas.datagen.models

import net.denthls.mineralas.Mineralas
import net.denthls.mineralas.registry.SamplesRegistry.samplesId
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.*
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import java.util.*

class ModelGen(dataGenerator: FabricDataGenerator) : FabricModelProvider(dataGenerator) {
    private val ore: TextureKey = TextureKey.of("ore")
    private val _ore: TextureKey = TextureKey.of("_ore")
    private val stone: TextureKey = TextureKey.of("stone")
    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator) {
        samplesId.minus("stone_sample").forEach { element ->
            registerSample(blockStateModelGenerator, element)
        }
    }

    override fun generateItemModels(itemModelGenerator: ItemModelGenerator) {}

    private fun registerSample(blockStateModelGenerator: BlockStateModelGenerator, id: String) {
        blockStateModelGenerator.registerSingleton(
            Registry.BLOCK.get(Identifier(Mineralas.MI, id)),
            TextureMap().put(TextureKey.PARTICLE, Identifier("minecraft:block/stone"))
                .put(ore, Identifier(Mineralas.MI, "block/$id"))
                .put(_ore, Identifier(Mineralas.MI, "block/$id"))
                .put(stone, Identifier("minecraft:block/stone")),
            Model(
                Optional.of(Identifier(Mineralas.MI, "block/sample")),
                Optional.empty(),
                TextureKey.PARTICLE,
                ore,
                _ore,
                stone
            )
        )
    }
}