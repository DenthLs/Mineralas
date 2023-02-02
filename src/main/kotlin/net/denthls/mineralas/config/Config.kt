package net.denthls.mineralas.config

import kotlinx.serialization.Serializable
import net.fabricmc.loader.api.FabricLoader

@Serializable
data class Config(
    val _comment: Comment = Comment("First line is the rarity, the lower the value, the more deposits",
        "Second line is the size of deposit"),
    val hematiteDeposit: Deposit = Deposit(true, 10, 10),
    val limoniteDeposit: Deposit = Deposit(true, 15, 32),
    val malachiteDeposit: Deposit = Deposit(true, 15, 32),
    val azuriteDeposit: Deposit = Deposit(true, 15, 32),
    val nativeGoldDeposit: Deposit = Deposit(true, 15, 32),
    val ultrabasiteDeposit: Deposit = Deposit(true, 15, 32),
    val cinnabarDeposit: Deposit = Deposit(true, 15, 32),
    val quartzDeposit: Deposit = Deposit(true, 15, 32),
    val antimoniteDeposit: Deposit = Deposit(isLoaded("modern_industrialization"), 15, 32),
    val fossilCoalDeposit: Deposit = Deposit(true, 15, 32),
    val argentiteDeposit: Deposit = Deposit(true, 15, 32),
    val nevyanskiteDeposit: Deposit = Deposit(true, 15, 32),
    val cassiteriteDeposit: Deposit = Deposit(true, 15, 32),
    val pentlanditeDeposit: Deposit = Deposit(true, 15, 32),
    val haliteDeposit: Deposit = Deposit(true, 15, 32),
    val wolframiteDeposit: Deposit = Deposit(true, 15, 32),
    val uraniniteDeposit: Deposit = Deposit(true, 15, 32),
    val bauxiteDeposit: Deposit = Deposit(true, 15, 32),
    val galenaDeposit: Deposit = Deposit(true, 15, 32),
    val mozaniteDeposit: Deposit = Deposit(true, 15, 32),
) {
    @Serializable
    data class Comment(
        val firstLine: String = "",
        val secondLine: String= ""
    )
    @Serializable
    data class Deposit(
        val depositEnabled: Boolean = true,
        val depositRarity: Int = 10,
        val depositSize: Int = 16
    )
    private fun isLoaded(name: String): Boolean = FabricLoader.getInstance().isModLoaded(name)
}

private fun isLoaded(name: String): Boolean = FabricLoader.getInstance().isModLoaded(name)



