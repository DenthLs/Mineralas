package net.denthls.mitech.world.gen

import net.denthls.mitech.world.feature.ModPlacedFeatures
import net.fabricmc.fabric.api.biome.v1.BiomeModification
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.world.gen.GenerationStep

object ModOreGeneration {

    fun generateOres(){
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.NEUTRON_ORE_PLACED.key.get())
    }

}