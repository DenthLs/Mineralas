package net.denthls.mineralas

import net.denthls.mineralas.block.MnOres
import net.denthls.mineralas.block.MnSamples
import net.denthls.mineralas.datagen.tags.MnTags
import net.denthls.mineralas.item.MnItems
import net.denthls.mineralas.world.RemoveOres
import net.denthls.mineralas.world.feature.MnConfiguredFeatures
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory


object Mineralas : ModInitializer {

    const val MI = "mineralas"
    fun log(name: String) {
        LoggerFactory.getLogger(MI).info(name)
    }

    override fun onInitialize() {
        MnSamples.registerSamples()
        MnItems.registerItems()
        MnOres.registerOres()
        MnConfiguredFeatures.generateOres()
        MnTags.registerTags()
        RemoveOres.init()
    }
}
