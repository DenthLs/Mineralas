package net.denthls.mineralas.world.feature.featureConfigs

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.Identifier
import net.minecraft.util.dynamic.Codecs
import net.minecraft.world.gen.feature.FeatureConfig


class MnFeatureConfig(
    val sampleId: Identifier,
    val oreId: Identifier,
    val size: Int,
) : FeatureConfig {
    companion object {
        val CODEC: Codec<MnFeatureConfig> =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<MnFeatureConfig> ->
                instance.group(
                    Identifier.CODEC.fieldOf("sampleId").forGetter(MnFeatureConfig::sampleId),
                    Identifier.CODEC.fieldOf("oreId").forGetter(MnFeatureConfig::oreId),
                    Codecs.POSITIVE_INT.fieldOf("size").forGetter(MnFeatureConfig::size),
                ).apply(instance) { sampleId: Identifier, oreId: Identifier, size: Int ->
                    MnFeatureConfig(sampleId, oreId, size)
                }
            }
    }
}