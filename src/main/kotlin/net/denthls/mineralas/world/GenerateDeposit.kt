package net.denthls.mineralas.world

import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkSectionPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.ChunkSectionCache
import net.minecraft.world.Heightmap
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreFeatureConfig
import java.util.*
import java.util.function.Function
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random
import kotlin.random.asJavaRandom

object GenerateDeposit {
    fun generateOre(
        originPos: BlockPos,
        world: StructureWorldAccess,
        size: Int,
        targets: List<OreFeatureConfig.Target>,
        height: String
    ) {
        val newY = when (height) {
            "stone" -> Random.nextInt(15, 45)
            "deepslate" -> Random.nextInt(-45, -15)
            else -> Random.nextInt(15, 45)
        }
        val blockPos = BlockPos(originPos.x, newY, originPos.z)
        val f = Random.nextFloat() * 3.1415927f
        val g = size.toFloat() / 8.0f
        val i = MathHelper.ceil((size.toFloat() / 16.0f * 2.0f + 1.0f) / 2.0f)
        val d = blockPos.x.toDouble() + sin(f.toDouble()) * g.toDouble()
        val e = blockPos.x.toDouble() - sin(f.toDouble()) * g.toDouble()
        val h = blockPos.z.toDouble() + cos(f.toDouble()) * g.toDouble()
        val j = blockPos.z.toDouble() - cos(f.toDouble()) * g.toDouble()
        val l = (blockPos.y + Random.nextInt(3) - 2).toDouble()
        val m = (blockPos.y + Random.nextInt(3) - 2).toDouble()
        val n = blockPos.x - MathHelper.ceil(g) - i
        val o = blockPos.y - 2 - i
        val p = blockPos.z - MathHelper.ceil(g) - i
        val q = 2 * (MathHelper.ceil(g) + i)
        val r = 2 * (2 + i)
        (n..n + q).forEach { s ->
            (p..p + q).forEach { t ->
                if (o <= world.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, s, t)) {
                    return generateVeinPart(
                        world,
                        size,
                        targets,
                        d,
                        e,
                        h,
                        j,
                        l,
                        m,
                        n,
                        o,
                        p,
                        q,
                        r
                    )
                }
            }
        }
    }

    private fun generateVeinPart(
        world: StructureWorldAccess,
        size: Int,
        targets: List<OreFeatureConfig.Target>,
        startX: Double,
        endX: Double,
        startZ: Double,
        endZ: Double,
        startY: Double,
        endY: Double,
        x: Int,
        y: Int,
        z: Int,
        horizontalSize: Int,
        verticalSize: Int
    ) {
        var i = 0
        val bitSet = BitSet(horizontalSize * verticalSize * horizontalSize)
        val mutable = BlockPos.Mutable()
        val ds = DoubleArray(size * 4)
        var k = 0
        var d: Double
        var e: Double
        var g: Double
        var h: Double
        while (k < size) {
            val f = k.toFloat() / size.toFloat()
            d = MathHelper.lerp(f.toDouble(), startX, endX)
            e = MathHelper.lerp(f.toDouble(), startY, endY)
            g = MathHelper.lerp(f.toDouble(), startZ, endZ)
            h = Random.nextDouble() * size.toDouble() / 16.0
            ds[k * 4 + 0] = d
            ds[k * 4 + 1] = e
            ds[k * 4 + 2] = g
            ds[k * 4 + 3] = ((MathHelper.sin(3.1415927f * f) + 1.0f).toDouble() * h + 1.0) / 2.0
            ++k
        }
        var m: Int
        k = 0
        while (k < size - 1) {
            if (ds[k * 4 + 3] > 0.0) {
                m = k + 1
                while (m < size) {
                    if (ds[m * 4 + 3] > 0.0) {
                        d = ds[k * 4 + 0] - ds[m * 4 + 0]
                        e = ds[k * 4 + 1] - ds[m * 4 + 1]
                        g = ds[k * 4 + 2] - ds[m * 4 + 2]
                        h = ds[k * 4 + 3] - ds[m * 4 + 3]
                        if (h * h > d * d + e * e + g * g) if (h > 0.0) ds[m * 4 + 3] = -1.0 else ds[k * 4 + 3] = -1.0
                    }
                    ++m
                }
            }
            ++k
        }
        val chunkSectionCache = ChunkSectionCache(world)
        runCatching {
            m = 0
            while (m < size) {
                d = ds[m * 4 + 3]
                if (d >= 0.0) {
                    e = ds[m * 4 + 0]
                    g = ds[m * 4 + 1]
                    h = ds[m * 4 + 2]
                    val n = MathHelper.floor(e - d).coerceAtLeast(x)
                    val o = MathHelper.floor(g - d).coerceAtLeast(y)
                    val p = MathHelper.floor(h - d).coerceAtLeast(z)
                    val q = MathHelper.floor(e + d).coerceAtLeast(n)
                    val r = MathHelper.floor(g + d).coerceAtLeast(o)
                    val s = MathHelper.floor(h + d).coerceAtLeast(p)
                    for (t in n..q) {
                        val u = (t.toDouble() + 0.5 - e) / d
                        if (u * u < 1.0) {
                            for (v in o..r) {
                                val w = (v.toDouble() + 0.5 - g) / d
                                if (u * u + w * w < 1.0) {
                                    for (aa in p..s) {
                                        val ab = (aa.toDouble() + 0.5 - h) / d
                                        if (u * u + w * w + ab * ab < 1.0 && !world.isOutOfHeightLimit(v)) {
                                            val ac =
                                                t - x + (v - y) * horizontalSize + (aa - z) * horizontalSize * verticalSize
                                            if (!bitSet[ac]) {
                                                bitSet.set(ac)
                                                mutable[t, v] = aa
                                                if (world.isValidForSetBlock(mutable)) {
                                                    val chunkSection = chunkSectionCache.getSection(mutable)
                                                    if (chunkSection != null) {
                                                        val ad = ChunkSectionPos.getLocalCoord(t)
                                                        val ae = ChunkSectionPos.getLocalCoord(v)
                                                        val af = ChunkSectionPos.getLocalCoord(aa)
                                                        val blockState = chunkSection.getBlockState(ad, ae, af)
                                                        val var57: Iterator<*> = targets.iterator()
                                                        while (var57.hasNext()) {
                                                            val target = var57.next() as OreFeatureConfig.Target
                                                            Objects.requireNonNull(chunkSectionCache)
                                                            if (shouldPlace(
                                                                    blockState,
                                                                    { pos: BlockPos? ->
                                                                        chunkSectionCache.getBlockState(
                                                                            pos
                                                                        )
                                                                    }, 1.0f, target, mutable
                                                                )
                                                            ) {
                                                                chunkSection.setBlockState(
                                                                    ad,
                                                                    ae,
                                                                    af,
                                                                    target.state,
                                                                    false
                                                                )
                                                                ++i
                                                                break
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                ++m
            }
        }.onFailure { throwable ->
            runCatching {
                chunkSectionCache.close()
            }.onFailure {
                throwable.addSuppressed(it)
            }
            throw throwable
        }
        chunkSectionCache.close()
    }

    private fun shouldPlace(
        state: BlockState?,
        posToState: Function<BlockPos?, BlockState>?,
        discardOnAirChance: Float,
        target: OreFeatureConfig.Target,
        pos: BlockPos.Mutable?
    ): Boolean = when {
        !target.target.test(state, Random.asJavaRandom()) -> false
        shouldNotDiscard(discardOnAirChance) -> true
        else -> !Feature.isExposedToAir(posToState, pos)
    }

    private fun shouldNotDiscard(chance: Float): Boolean = when {
        chance <= 0.0f -> true
        chance >= 1.0f -> false
        else -> Random.nextFloat() >= chance
    }
}