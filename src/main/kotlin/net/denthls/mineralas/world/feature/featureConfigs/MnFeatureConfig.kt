package net.denthls.mineralas.world.feature.featureConfigs

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.Identifier
import net.minecraft.util.dynamic.Codecs
import net.minecraft.world.gen.feature.FeatureConfig


class MnFeatureConfig(
    val blockId: Identifier,
    val oreBlockId: Identifier,
    val size: Int,
    val height: String
) : FeatureConfig {
    companion object {
        val CODEC: Codec<MnFeatureConfig> =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<MnFeatureConfig> ->
                instance.group(
                    Identifier.CODEC.fieldOf("blockId").forGetter(MnFeatureConfig::blockId),
                    Identifier.CODEC.fieldOf("oreBlockId").forGetter(MnFeatureConfig::oreBlockId),
                    Codecs.POSITIVE_INT.fieldOf("size").forGetter(MnFeatureConfig::size),
                    Codec.STRING.fieldOf("height").forGetter(MnFeatureConfig::height)
                ).apply(instance) { blockId: Identifier, oreBlockId: Identifier, size: Int, height: String ->
                    MnFeatureConfig(blockId, oreBlockId, size, height)
                }
            }
    }
}