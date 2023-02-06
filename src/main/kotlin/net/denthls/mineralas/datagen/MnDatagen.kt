package net.denthls.mineralas.datagen

import net.denthls.mineralas.datagen.models.ModelGen
import net.denthls.mineralas.datagen.tags.BlockTagGen
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object MnDatagen : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        fabricDataGenerator.addProvider {
            BlockTagGen(it)
        }
        fabricDataGenerator.addProvider {
            ModelGen(it)
        }
    }
}