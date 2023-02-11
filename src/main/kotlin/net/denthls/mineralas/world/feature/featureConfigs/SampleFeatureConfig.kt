package net.denthls.mineralas.world.feature.featureConfigs

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.Identifier
import net.minecraft.util.dynamic.Codecs
import net.minecraft.world.gen.feature.FeatureConfig

class SampleFeatureConfig(
    val sampleId: Identifier,
    val rarity: Float
) : FeatureConfig {
    companion object {
        val CODEC: Codec<SampleFeatureConfig> =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<SampleFeatureConfig> ->
                instance.group(
                    Identifier.CODEC.fieldOf("sampleId").forGetter(SampleFeatureConfig::sampleId),
                    Codecs.POSITIVE_FLOAT.fieldOf("rarity").forGetter(SampleFeatureConfig::rarity),
                ).apply(instance) { sampleId: Identifier, rarity: Float ->
                    SampleFeatureConfig(sampleId, rarity)
                }
            }
    }
}