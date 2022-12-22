package net.denthls.mitech.item.custom

import net.denthls.mitech.item.MItems
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.Formatting
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import java.util.*


class SuperIngot(settings: Settings?) : Item(settings) {

        var canUse: Boolean = false
        var coolDown = 1
    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        if (!world.isClient) {
            if (entity is PlayerEntity && stack.item.equals(MItems.SUPER_INGOT) && selected) {
                if (entity.hungerManager.isNotFull) {
                    if (coolDown > 0) {
                        coolDown--; canUse = false
                    } else canUse = true
                    if (canUse) {
                        entity.hungerManager.add(1, 1f); coolDown = 30
                    }
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if(!world.isClient) {
            if (user.hungerManager.isNotFull) {
                user.hungerManager.add(10, 2f)
                user.itemCooldownManager.set(this, 20)
                val stack = user.getStackInHand(hand)
                if (user is ServerPlayerEntity) {
                    stack.damage(1, Random(0), user)
                }
            }
        }
        return super.use(world, user, hand)
    }

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext?) {
        if (!Screen.hasShiftDown()) { tooltip.add(Text.of("Please Shift for more info"))} else {
            tooltip.add(TranslatableText("tooltip.mitech.super_ingot_0").formatted(Formatting.YELLOW))
            tooltip.add(TranslatableText("tooltip.mitech.super_ingot_1").formatted(Formatting.YELLOW))
        }
        super.appendTooltip(stack, world, tooltip, context)
    }
}