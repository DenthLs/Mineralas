package net.denthls.mineralas.world.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.Identifier
import net.minecraft.util.dynamic.Codecs
import net.minecraft.world.gen.feature.FeatureConfig


class MnFeatureConfig(
    val blockId: Identifier,
    val oreBlockId: Identifier,
    val size: Int,
    val toReplace: Int
) : FeatureConfig {
    companion object {
        val CODEC: Codec<MnFeatureConfig> =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<MnFeatureConfig> ->
                instance.group(
                    Identifier.CODEC.fieldOf("blockId").forGetter(MnFeatureConfig::blockId),
                    Identifier.CODEC.fieldOf("oreBlockId").forGetter(MnFeatureConfig::oreBlockId),
                    Codecs.POSITIVE_INT.fieldOf("size").forGetter(MnFeatureConfig::size),
                    Codecs.NONNEGATIVE_INT.fieldOf("toReplace").forGetter(MnFeatureConfig::toReplace)
                ).apply(instance) { blockId: Identifier, oreBlockId: Identifier,
                                    size: Int, toReplace: Int ->
                    MnFeatureConfig(blockId, oreBlockId, size, toReplace)
                }
            }
    }
}