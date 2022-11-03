package net.denthls.mitech

import net.denthls.mitech.item.ModItems
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory
object MiTech : ModInitializer {

    val MI = "mitech"
    val LOGGER = LoggerFactory.getLogger(MI)

    override fun onInitialize() {

        ModItems.registerModItems()

    }

}
