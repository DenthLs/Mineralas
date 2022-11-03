package net.denthls.mitech.world.feature

import net.minecraft.util.registry.RegistryEntry
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.feature.PlacedFeatures
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier
import net.minecraft.world.gen.placementmodifier.PlacementModifier
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier

object ModPlacedFeatures {

    val NEUTRON_ORE_PLACED: RegistryEntry<PlacedFeature> = PlacedFeatures.register("neutron_blocks_placed",
        ModConfiguredFeatures.NEUTRON_BLOCK, modifiersWithCount(1,10,
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(80))))

    fun modifiers(countModifier: PlacementModifier, chanceModifier: PlacementModifier, heightModifier: PlacementModifier): List<PlacementModifier> {
        return listOf(countModifier, chanceModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of())
    }
    fun modifiersWithCount(count: Int, chance: Int, heightModifier: PlacementModifier): List<PlacementModifier> {
        return modifiers(CountPlacementModifier.of(count),RarityFilterPlacementModifier.of(chance), heightModifier)
    }

}
