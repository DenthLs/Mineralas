package net.denthls.mineralas.item.custom

import net.denthls.mineralas.datagen.tags.MnTags
import net.minecraft.block.Block
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.ActionResult
import net.minecraft.util.Formatting
import net.minecraft.world.World


open class DowsingRod(settings: Settings) : Item(settings) {
    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        if (context.world.isClient()) {
            val positionClicked = context.blockPos
            val player = context.player
            var foundBlock = false
            for (i in 0..positionClicked.y + 64) {
                val blockBelow = context.world.getBlockState(positionClicked.down(i)).block
                if (isValuableBlock(blockBelow)) {
                    outputValuableCoordinates(player!!, blockBelow)
                    foundBlock = true
                    break
                }
            }
            if (!foundBlock) {
                player!!.sendMessage(TranslatableText("item.mineralas.dowsing_rod.no_valuables"), false)
            }
        }


        context.stack.damage(
            1, context.player
        ) { player -> player!!.sendToolBreakStatus(player.activeHand) }
        return super.useOnBlock(context)
    }

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        if (!Screen.hasShiftDown()) {
            tooltip.add(TranslatableText("item.mineralas.dowsing_rod.tooltip_0"))
        } else {
            tooltip.add(TranslatableText("item.mineralas.dowsing_rod.tooltip_1").formatted(Formatting.YELLOW))
            tooltip.add(TranslatableText("item.mineralas.dowsing_rod.tooltip_2").formatted(Formatting.YELLOW))
        }
        super.appendTooltip(stack, world, tooltip, context)
    }

    private fun outputValuableCoordinates(player: PlayerEntity, blockBelow: Block) {
        player.sendMessage(
            LiteralText(
                "Found " + blockBelow.asItem().name.string
            ), false
        )
    }

    private fun isValuableBlock(block: Block): Boolean {
        if (block.defaultState.isIn(MnTags.ORES)) return true
        return false
    }
}



