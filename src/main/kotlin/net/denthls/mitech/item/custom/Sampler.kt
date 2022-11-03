package net.denthls.mitech.item.custom

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class Sampler (settings: Settings): Item(settings) {
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (!world.isClient && hand == Hand.MAIN_HAND) {
            checkChunk(user, world)
        }
        return super.use(world, user, hand)
    }

    fun checkChunk(player: PlayerEntity, world: World) {
        var startX = player.chunkPos.startX
        if (startX < 0) {
            startX += 1
        }
        var startZ = player.chunkPos.startZ
        if (startZ < 0) {
            startZ += 1
        }
        var endX = player.chunkPos.endX
        if (endX < 0) {
            endX += 1
        }
        var endZ = player.chunkPos.endZ
        if (endZ < 0) {
            endZ += 1
        }

        var iron = 0; var gold = 0; var coal = 0; var lapis = 0; var diamond = 0; var redstone = 0; var emerald = 0; var copper = 0
        var tin = 0; var bauxite = 0;



        for (y in -63..318) {
            for (x in startX..endX) {
                for (z in startZ..endZ) {
                    val block = world.getBlockState(BlockPos(x, y, z)).block
                    when (block.toString()){
                        regM("iron_ore") -> iron++; regM("deepslate_iron_ore") -> iron++
                        regM("gold_ore") -> gold++; regM("deepslate_gold_ore") -> gold++
                        regM("coal_ore") -> coal++; regM("deepslate_coal_ore") -> coal++
                        regM("lapis_ore") -> lapis++; regM("deepslate_lapis_ore") -> lapis++
                        regM("redstone_ore") -> redstone++; regM("deepslate_redstone_ore") -> redstone++
                        regM("diamond_ore") -> diamond++; regM("deepslate_diamond_ore") -> diamond++
                        regM("emerald_ore") -> emerald++; regM("deepslate_emerald_ore") -> emerald++
                        regM("copper_ore") -> copper++; regM("deepslate_copper_ore") -> copper++
                        regT("tin_ore") -> tin++; regT("deepslate_tin_ore") -> tin++
                        regI("tin_ore") -> tin++; regI("deepslate_tin_ore") -> tin++

                    }
                }
            }
        }
        //Vanilla Blocks
        send("Copper Ore", copper, player)
        send("Iron Ore", iron, player)
        send("Gold Ore", gold, player)
        send("Coal Ore", coal, player)
        send("Lapis Ore", lapis, player)
        send("Redstone Ore", redstone, player)
        send("Diamond Ore", diamond, player)
        send("Emerald Ore", emerald, player)
        //Mod Blocks
        send("Tin Ore", tin, player)

        player.sendMessage(Text.of(""), false)


    }

    fun regM(name: String): String{
        return "Block{minecraft:$name}"
    }
    fun regT(name: String): String{
        return "Block{techreborn:$name}"
    }
    fun regI(name: String): String{
        return "Block{modern_industrialization:$name}"
    }

    fun send(name: String, ore: Int, player: PlayerEntity){
        if (ore > 0){player.sendMessage(Text.of("$name: $ore"), false)}

    }


}


