package net.denthls.mitech.screen

import net.denthls.mitech.MiTech
import net.minecraft.screen.ScreenHandlerFactory
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


class MTScreenHandlers {

    var COLLECTOR_SCREEN_HANDLER: ScreenHandlerType<CollectorScreenHandler> =
        Registry.register(Registry.SCREEN_HANDLER,
            Identifier(MiTech.MI, "collector_block"),
            ScreenHandlerFactory { syncId, inv, player -> CollectorScreenHandler(syncId, inv) }
        )
}