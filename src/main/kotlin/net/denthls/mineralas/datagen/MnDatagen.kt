package net.denthls.mineralas.datagen

import net.denthls.mineralas.datagen.tags.MnBlockTagGen
import net.denthls.mineralas.datagen.tags.MnItemTagGen
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object MnDatagen : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        fabricDataGenerator.addProvider { fabricDataGenerator: FabricDataGenerator ->
            MnBlockTagGen(fabricDataGenerator)
        }
        fabricDataGenerator.addProvider { fabricDataGenerator: FabricDataGenerator ->
            MnItemTagGen(fabricDataGenerator)
        }
    }
}