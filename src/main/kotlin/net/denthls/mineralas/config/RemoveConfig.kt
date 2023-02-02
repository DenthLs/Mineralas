package net.denthls.mineralas.config

import kotlinx.serialization.Serializable

@Serializable
data class RemoveConfig(
//    val getFeatures: Features = Features(true, listOf("minecraft:plains")),
    val appliedEnergisticsFeatures: Remove = Remove(true, listOf("quartz_ore")),
    val modernIndustrializationFeatures: Remove = Remove(
        true,
        listOf(
            "antimony",
            "bauxite",
            "iridium",
            "lead",
            "lignite_coal",
            "mozanite",
            "nickel",
            "salt",
            "tin",
            "tungsten",
            "uranium"
        )
    ),
    val techRebornFeatures: Remove = Remove(
        true,
        listOf(
            "bauxite_ore",
            "cinnabar_ore",
            "galena_ore",
            "sapphire_ore",
            "lead_ore",
            "ruby_ore",
            "silver_ore",
            "tin_ore",
            "iridium_ore"
        )
    ),
    val powahFeatures: Remove = Remove(true, listOf("uraninite_ore_poor", "uraninite_ore", "uraninite_ore_dense")),
    val defaultFeatures: Remove = Remove(
        true, listOf(
            "copper",
            "copper_large",
            "coal_lower",
            "coal_upper",
            "iron_small",
            "iron_middle",
            "iron_upper",
            "gold",
            "gold_extra",
            "gold_lower",
            "lapis",
            "lapis_buried",
            "redstone",
            "redstone_lower",
            "diamond",
            "diamond_large",
            "diamond_buried",
            "emerald"
        )
    ),
    val comment: Comment = Comment("You can also add features from other mods, but you must write the namespace (Mod name)"),
    val anotherFeatures: Remove = Remove(false, listOf(""))
) {
    @Serializable
    data class Remove(
        val removeEnabled: Boolean = true,
        val toRemove: List<String> = listOf("")
    )

    @Serializable
    data class Comment(
        val _comment: String = ""
    )

    @Serializable
    data class Features(
        val logEnabled: Boolean = false,
        val biomes: List<String> = listOf("")
    )
}