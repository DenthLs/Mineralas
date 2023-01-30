package net.denthls.mineralas.config

import kotlinx.serialization.Serializable
import java.util.Objects

@Serializable
data class Config(
    val _comment: Comment = Comment("First line is the rarity, the lower the value, the more deposits",
        "Second line is the size of deposit"),
    val hematiteDeposit: Deposit = Deposit(true, 10, 10),
    val deepslateHematiteDeposit: Deposit = Deposit(true, 15, 32)
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
}



