package net.denthls.mineralas.config

import kotlinx.serialization.Serializable
import net.fabricmc.loader.api.FabricLoader

@Serializable
data class Config(
    val _comment: Comment = Comment(
        "First line is the rarity, the lower the value, the more deposits",
        "Second line is the size of deposit"
    ),
    val ironDeposit: Deposit = Deposit(true, 50, 64),
    val copperDeposit: Deposit = Deposit(true, 50, 64),
    val goldDeposit: Deposit = Deposit(true, 50, 64),
    val coalDeposit: Deposit = Deposit(true, 50, 64),
    val lapisDeposit: Deposit = Deposit(true, 50, 32),
    val redstoneDeposit: Deposit = Deposit(true, 50, 32),
    val diamondDeposit: Deposit = Deposit(true, 50, 32),
    val emeraldDeposit: Deposit = Deposit(true, 50, 32),
    val antimonyDeposit: Deposit = Deposit(MI, 50, 32),
    val bauxiteDeposit: Deposit = Deposit(MI || TR, 50, 32),
    val tinDeposit: Deposit = Deposit(MI || TR || IR, 50, 32),
    val iridiumDeposit: Deposit = Deposit(MI, 50, 32),
    val leadDeposit: Deposit = Deposit(MI || TR || IR, 50, 32),
    val ligniteCoalDeposit: Deposit = Deposit(MI, 50, 32),
    val mozaniteDeposit: Deposit = Deposit(MI, 50, 32),
    val nickelDeposit: Deposit = Deposit(MI, 50, 32),
    val saltDeposit: Deposit = Deposit(MI, 50, 32),
    val tungstenDeposit: Deposit = Deposit(MI || IR, 50, 32),
    val uraniumDeposit: Deposit = Deposit(MI || PW, 50, 32),
    val rubyDeposit: Deposit = Deposit(TR, 50, 32),
    val sapphireDeposit: Deposit = Deposit(TR, 50, 32),
    val silverDeposit: Deposit = Deposit(TR || IR, 50, 32),
    val quartzDeposit: Deposit = Deposit(AE, 50, 32),
    val nikoliteDeposit: Deposit = Deposit(IR, 50, 32)
) {
    @Serializable
    data class Comment(
        val firstLine: String = "",
        val secondLine: String = ""
    )

    @Serializable
    data class Deposit(
        val depositEnabled: Boolean = true,
        val depositRarity: Int = 10,
        val depositSize: Int = 16
    )
}

private val PW: Boolean = isLoaded("powah")
private val AE: Boolean = isLoaded("ae2")
private val TR: Boolean = isLoaded("tech_reborn")
private val MI: Boolean = isLoaded("modern_industrialization")
private val IR: Boolean = isLoaded("indrev")
private fun isLoaded(name: String): Boolean = FabricLoader.getInstance().isModLoaded(name)



