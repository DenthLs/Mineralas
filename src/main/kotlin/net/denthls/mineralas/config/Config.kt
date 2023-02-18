package net.denthls.mineralas.config

import kotlinx.serialization.Serializable
import net.fabricmc.loader.api.FabricLoader

@Serializable
data class Config(
    val stoneSample: Sample = Sample(true, 0.02f),
    val ironDeposit: Deposit = Deposit(true, 50, 96),
    val copperDeposit: Deposit = Deposit(true, 30, 128),
    val goldDeposit: Deposit = Deposit(true, 60, 64),
    val coalDeposit: Deposit = Deposit(true, 30, 128),
    val lapisDeposit: Deposit = Deposit(true, 60, 96),
    val redstoneDeposit: Deposit = Deposit(true, 50, 96),
    val diamondDeposit: Deposit = Deposit(true, 90, 32),
    val emeraldDeposit: Deposit = Deposit(true, 100, 24),
    val antimonyDeposit: Deposit = Deposit(MI, 60, 32),
    val bauxiteDeposit: Deposit = Deposit(MI || TR, 40, 64),
    val tinDeposit: Deposit = Deposit(MI || TR || IR, 30, 128),
    val iridiumDeposit: Deposit = Deposit(MI, 100, 24),
    val leadDeposit: Deposit = Deposit(MI || TR || IR, 60, 48),
    val ligniteCoalDeposit: Deposit = Deposit(MI, 30, 128),
    val mozaniteDeposit: Deposit = Deposit(MI, 80, 24),
    val nickelDeposit: Deposit = Deposit(MI, 60, 32),
    val saltDeposit: Deposit = Deposit(MI, 40, 64),
    val tungstenDeposit: Deposit = Deposit(MI || IR, 70, 48),
    val uraniumDeposit: Deposit = Deposit(MI || PW, 70, 48),
    val rubyDeposit: Deposit = Deposit(TR, 90, 24),
    val sapphireDeposit: Deposit = Deposit(TR, 90, 24),
    val silverDeposit: Deposit = Deposit(TR || IR, 70, 48),
    val quartzDeposit: Deposit = Deposit(AE, 40, 96),
    val nikoliteDeposit: Deposit = Deposit(IR, 50, 32),
    val quantumDeposit: Deposit = Deposit(QC, 70, 16)
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

    @Serializable
    data class Sample(
        val sampleEnabled: Boolean = true,
        val sampleRarity: Float = 0.06f
    )
}

private val PW: Boolean = isLoaded("powah")
private val AE: Boolean = isLoaded("ae2")
private val TR: Boolean = isLoaded("tech_reborn")
private val MI: Boolean = isLoaded("modern_industrialization")
private val IR: Boolean = isLoaded("indrev")
private val QC: Boolean = isLoaded("qcraft")
private val MM: Boolean = isLoaded("mythic_metals")
private fun isLoaded(name: String): Boolean = FabricLoader.getInstance().isModLoaded(name)



