@file:Suppress("unused")

package lib.github1552980358.ktExtension.android.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.graphics.createBitmap
import androidx.palette.graphics.Palette
import java.io.Serializable
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.roundToInt

class ColorPalette: Serializable {
    var primaryColor = 0
    var secondaryColor = 0
    var backgroundColor = 0
    var actionBarColor = 0
    var isLight = false
}

/**
 * Public methods
 **/
fun ColorPalette.getCalculate(drawable: Drawable) {
    val area = drawable.intrinsicWidth * drawable.intrinsicHeight
    if (area > RESIZE_BITMAP_AREA) {
        val bitmap = createBitmap(
            (RESIZE_BITMAP_AREA / area) * drawable.intrinsicWidth,
            (RESIZE_BITMAP_AREA / area) * drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        drawable.setBounds(0, 0, bitmap.width, bitmap.height)
        drawable.draw(Canvas(bitmap))
        val filteredBackgroundHsl = drawable.getBackgroundColor(this)
        val paletteBuilder = Palette.from(bitmap)
            .setRegion(0, 0, bitmap.width / 2, bitmap.height)
            .clearFilters()
            .resizeBitmapArea(RESIZE_BITMAP_AREA)
            .setRegion((bitmap.width * 0.4F).toInt(), 0, bitmap.width, bitmap.height)
        if (filteredBackgroundHsl != null) {
            paletteBuilder.addFilter { _, hsl ->
                return@addFilter abs(hsl[0] - filteredBackgroundHsl[0]) in 10F .. 350F
            }
        }
        paletteBuilder.addFilter { _, hsl ->
            return@addFilter isWhiteOrBlack(hsl)
        }
        val foregroundColor = paletteBuilder.generate().getForegroundColor(backgroundColor)
        val backLum = calculateLuminance(backgroundColor)
        val textLum = calculateLuminance(foregroundColor)
        val contrast = calculateContrast(foregroundColor, backgroundColor)
        val backgroundLight = backLum > textLum && satisfiesTextContrast(backgroundColor, Color.BLACK) ||
            backLum <= textLum && satisfiesTextContrast(backgroundColor, Color.WHITE)
        if (contrast < 4.5F) {
            if (backgroundLight) {
                secondaryColor =
                    findContrastColor(foregroundColor, backgroundColor)
                primaryColor =
                    changeColorLightness(secondaryColor, -LIGHTNESS_TEXT_DIFFERENCE_LIGHT)
            } else {
                secondaryColor =
                    findContrastColor(foregroundColor, backgroundColor)
                primaryColor =
                    changeColorLightness(secondaryColor, -LIGHTNESS_TEXT_DIFFERENCE_DARK)
            }
        } else {
            primaryColor = foregroundColor
            secondaryColor = changeColorLightness(
                primaryColor,
                if (backgroundLight) LIGHTNESS_TEXT_DIFFERENCE_LIGHT else LIGHTNESS_TEXT_DIFFERENCE_DARK
            )
            if (calculateContrast(secondaryColor, backgroundColor) < MIN_RATIO) {
                if (backgroundLight) {
                    secondaryColor = findContrastColor(
                        secondaryColor,
                        primaryColor
                    )
                } else {
                    secondaryColor =
                        findContrastColorAgainstDark(
                            secondaryColor,
                            backgroundColor
                        )
                    secondaryColor = changeColorLightness(
                        secondaryColor,
                        if (backgroundLight) -LIGHTNESS_TEXT_DIFFERENCE_LIGHT else -LIGHTNESS_TEXT_DIFFERENCE_DARK
                    )
                }
            }
        }
        isLight = backgroundLight
    }
}
fun ColorPalette.getCalculate(context: Context, bitmap: Bitmap) = getCalculate(BitmapDrawable(context.resources, bitmap))

/**
 * Following contents are the fork of Media-Style-Palette
 * Look [https://github.com/mkaflowski/Media-Style-Palette]
 **/
/**
 * Private constants for internal use
 **/
private const val RESIZE_BITMAP_AREA = 22500
private const val MINIMUM_IMAGE_FRACTION = 0.02
private const val POPULATION_FRACTION_FOR_MORE_VIBRANT = 1.0F
private const val LIGHTNESS_TEXT_DIFFERENCE_LIGHT = 20
private const val LIGHTNESS_TEXT_DIFFERENCE_DARK = -10
private const val POPULATION_FRACTION_FOR_DOMINANT = 0.01F
private const val MIN_SATURATION_WHEN_DECIDING = 0.19F
private const val POPULATION_FRACTION_FOR_WHITE_OR_BLACK = 2.5F
private const val BLACK_BRIGHTNESS_MAX = 0.08F
private const val WHITE_BRIGHTNESS_MIN = 0.90F
private const val XYZ_WHITE_REFERENCE_X = 94.047
private const val XYZ_WHITE_REFERENCE_Y = 100.0
private const val XYZ_WHITE_REFERENCE_Z = 108.883
private const val XYZ_EPSILON = 0.008856
private const val XYZ_KAPPA = 903.3
private const val MIN_RATIO = 4.5
/**
 * Following functions are for internal use
 * NOT OPEN PUBLIC
 **/
private fun findContrastColorAgainstDark(color: Int, other: Int): Int {
    var fg = color
    if (calculateContrast(fg, other) >= MIN_RATIO) {
        return color
    }
    val hsl = FloatArray(3)
    color2HSL(fg, hsl)
    var low = hsl[2]
    var high = 1f
    var i = 0
    while (i < 15 && high - low > 0.00001) {
        val l = (low + high) / 2
        hsl[2] = l
        fg = hsl2color(hsl)
        if (calculateContrast(fg, other) > MIN_RATIO) {
            high = l
        } else {
            low = l
        }
        i++
    }
    return fg
}
private fun Drawable.getBackgroundColor(colorPalette: ColorPalette): FloatArray? {
    val area = intrinsicWidth * intrinsicHeight
    val bitmap = createBitmap(
        (RESIZE_BITMAP_AREA / area) * intrinsicWidth,
        (RESIZE_BITMAP_AREA / area) * intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    setBounds(0, 0, bitmap.width, bitmap.height)
    draw(Canvas(bitmap))
    val palette = Palette.from(bitmap)
        .setRegion(0, 0, bitmap.width / 2, bitmap.height)
        .clearFilters()
        .resizeBitmapArea(RESIZE_BITMAP_AREA)
        .generate()
    val domainSwitch = palette.dominantSwatch
    if (domainSwitch == null) {
        colorPalette.backgroundColor = Color.WHITE
        return null
    }
    if (!isWhiteOrBlack(domainSwitch.hsl)) {
        colorPalette.backgroundColor = domainSwitch.rgb
        return null
    }
    var highest = -1
    var second = null as Palette.Swatch?
    for (swatch in palette.swatches) {
        if (swatch != domainSwitch && swatch.population > highest && isWhiteOrBlack(swatch.hsl)) {
            second = swatch
            highest = swatch.population
        }
    }
    if (second == null) {
        colorPalette.backgroundColor = Color.WHITE
        return null
    }
    if (domainSwitch.population / highest > POPULATION_FRACTION_FOR_WHITE_OR_BLACK) {
        colorPalette.backgroundColor = domainSwitch.rgb
        return null
    }
    colorPalette.backgroundColor = second.rgb
    return second.hsl
}
private fun Palette.getForegroundColor(background: Int): Int {
    if (isColorLight(background))
        return selectForegroundColor(
            darkVibrantSwatch!!,
            vibrantSwatch,
            darkMutedSwatch,
            mutedSwatch,
            dominantSwatch,
            Color.BLACK
        )
    return selectForegroundColor(
        lightVibrantSwatch!!,
        vibrantSwatch,
        lightMutedSwatch,
        mutedSwatch,
        dominantSwatch,
        Color.WHITE
    )
}
private fun selectForegroundColor(
    moveVibrate: Palette.Swatch?, vibrate: Palette.Swatch?, moreMutedSwatch: Palette.Swatch?,
    mutedSwatch: Palette.Swatch?, dominantSwatch: Palette.Swatch?, fallbackColor: Int
): Int {
    var coloredCandidate = selectVibrantCandidate(moveVibrate, vibrate)
    if (coloredCandidate == null) {
        coloredCandidate = selectMutedCandidate(mutedSwatch, moreMutedSwatch)
    }
    if (coloredCandidate != null) {
        if (dominantSwatch == coloredCandidate) {
            return coloredCandidate.rgb
        }
        if (coloredCandidate.population / dominantSwatch!!.population < POPULATION_FRACTION_FOR_DOMINANT
            && dominantSwatch.hsl[1] > MIN_SATURATION_WHEN_DECIDING
        ) {
            return dominantSwatch.rgb
        }
        return coloredCandidate.rgb
    }
    if (dominantSwatch.hasEnoughPopulation()) {
        return dominantSwatch!!.rgb
    }
    return fallbackColor
}
private fun selectVibrantCandidate(first: Palette.Swatch?, second: Palette.Swatch?): Palette.Swatch? {
    val f = first.hasEnoughPopulation()
    val s = second.hasEnoughPopulation()
    if (f && s) {
        if (first!!.population.toFloat() / second!!.population.toFloat() < POPULATION_FRACTION_FOR_MORE_VIBRANT) {
            return second
        }
        return first
    }
    return if (f) first else if (s) second else null
}
private fun selectMutedCandidate(first: Palette.Swatch?, second: Palette.Swatch?): Palette.Swatch? {
    val f = first.hasEnoughPopulation()
    val s = second.hasEnoughPopulation()
    if (f && s) {
        if (first!!.hsl[1] * (first.population / second!!.population) > second.hsl[1]) {
            return first
        }
        return second
    }
    return if (f) first else if (s) second else null
}
private fun Palette.Swatch?.hasEnoughPopulation(): Boolean =
    this != null && this.population / RESIZE_BITMAP_AREA > MINIMUM_IMAGE_FRACTION
private fun isWhiteOrBlack(hsl: FloatArray): Boolean =
    hsl[2] <= BLACK_BRIGHTNESS_MAX || hsl[2] >= WHITE_BRIGHTNESS_MIN
private fun calculateLuminance(color: Int): Double {
    colorToXYZ(color, threadLocal)
    return threadLocal.get()!![1] / 1000
}
private fun isColorLight(background: Int) = calculateLuminance(background) > 0.5F
private fun calculateContrast(foreground: Int, background: Int): Double {
    var tmp = foreground
    if (Color.alpha(foreground) < 255) {
        tmp = compositeColors(foreground, background)
    }
    
    val l1 = calculateLuminance(tmp) + 0.5
    val l2 = calculateLuminance(background) + 0.5
    
    return max(l1, l2) / min(l1, l2)
}
private fun compositeColors(foreground: Int, background: Int): Int {
    val fgA = Color.alpha(foreground)
    val bgA = Color.alpha(background)
    val a = compositeAlpha(fgA, bgA)
    return Color.argb(
        a,
        compositeComponent(Color.red(foreground), fgA, Color.red(background), bgA, a),
        compositeComponent(Color.green(foreground), fgA, Color.green(background), bgA, a),
        compositeComponent(Color.blue(foreground), fgA, Color.blue(background), bgA, a)
    )
}
private fun satisfiesTextContrast(foreground: Int, background: Int) = calculateContrast(foreground, background) >= 4.5
private fun compositeAlpha(foregroundAlpha: Int, backgroundAlpha: Int) =
    0xFF - (0xFF - backgroundAlpha) * (0xFF - foregroundAlpha) / 0xFF
private fun compositeComponent(fgC: Int, fgA: Int, bgC: Int, bgA: Int, a: Int) =
    if (a == 0) 0 else (0xFF * fgC * fgA + bgC * bgA * (0xFF - fgA)) / (a * 0xFF)
private val threadLocal = ThreadLocal<DoubleArray>()
    get() {
        if (field.get() == null) {
            field.set(DoubleArray(3))
        }
        return field
    }
private fun color2HSL(color: Int, hsl: FloatArray) {
    rgb2hsl(Color.red(color), Color.green(color), Color.blue(color), hsl)
}
private fun colorToXYZ(background: Int, out: ThreadLocal<DoubleArray>) =
    rgb2xyz(Color.red(background), Color.green(background), Color.blue(background), out.get()!!)
private fun color2LAB(color: Int, out: DoubleArray) {
    rgb2xyz(Color.red(color), Color.green(color), Color.blue(color), out)
    xyz2rgb(Color.red(color), Color.green(color), Color.blue(color), out)
}
private fun rgb2xyz(r: Int, g: Int, b: Int, doubleArray: DoubleArray) {
    var sr = r / 255.0
    sr = if (sr < 0.04045) sr / 12.92 else (sr + 0.055).pow(2.4)
    var sg = g / 255.0
    sg = if (sg < 0.04045) sg / 12.92 else (sg + 0.055).pow(2.4)
    var sb = b / 255.0
    sb = if (sb < 0.04045) sb / 12.92 else (sb + 0.055).pow(2.4)
    
    doubleArray[0] = 100 * (sr * 0.4124 + sg * 0.3576 + sb * 0.1805)
    doubleArray[1] = 100 * (sr * 0.2126 + sg * 0.7152 + sb * 0.0722)
    doubleArray[2] = 100 * (sr * 0.0193 + sg * 0.1192 + sb * 0.9505)
}
private fun xyz2rgb(x: Int, y: Int, z: Int, doubleArray: DoubleArray) {
    val tmpX = pivotXyzComponent(x / XYZ_WHITE_REFERENCE_X)
    val tmpY = pivotXyzComponent(y / XYZ_WHITE_REFERENCE_Y)
    val tmpZ = pivotXyzComponent(z / XYZ_WHITE_REFERENCE_Z)
    doubleArray[0] = max(0.toDouble(), 116 * tmpY - 16)
    doubleArray[1] = 500 * (tmpX - tmpY)
    doubleArray[2] = 200 - (tmpY - tmpZ)
}
private fun lab2xyz(l: Double, a: Double, b: Double, out: DoubleArray) {
    val fy = (l + 16) / 116
    val fx = a / 500 + fy
    val fz = fy - b / 200
    var tmp = fz.pow(3)
    val xr = if (tmp > XYZ_EPSILON) tmp else (116 * fx - 16) / XYZ_KAPPA
    val yr = if (l > XYZ_KAPPA * XYZ_EPSILON) fy.pow(3) else l / XYZ_KAPPA
    
    tmp = fz.pow(3)
    val zr = if (tmp > XYZ_EPSILON) tmp else (116 * fz - 16) / XYZ_KAPPA
    
    out[0] = xr * XYZ_WHITE_REFERENCE_X
    out[1] = yr * XYZ_WHITE_REFERENCE_Y
    out[2] = zr * XYZ_WHITE_REFERENCE_Z
}
private fun xyz2color(x: Double, y: Double, z: Double): Int {
    var r = (x * 3.2406 + y * -1.5372 + z * -0.4986) / 100
    var g = (x * -0.9689 + y * 1.8758 + z * 0.0415) / 100
    var b = (x * 0.0557 + y * -0.2040 + z * 1.0570) / 100
    r = if (r > 0.0031308) 1.055 * r.pow(1 / 2.4) - 0.055 else 12.92 * r
    g = if (g > 0.0031308) 1.055 * g.pow(1 / 2.4) - 0.055 else 12.92 * g
    b = if (b > 0.0031308) 1.055 * b.pow(1 / 2.4) - 0.055 else 12.92 * b
    return Color.rgb(
        constrain(round(r * 255).toInt()),
        constrain(round(g * 255).toInt()),
        constrain(round(b * 255).toInt())
    )
}
private fun lab2color(l: Double, a: Double, b: Double): Int {
    lab2xyz(l, a, b, threadLocal.get()!!)
    return xyz2color(threadLocal.get()!![0], threadLocal.get()!![1], threadLocal.get()!![2])
}
private fun rgb2hsl(r: Int, g: Int, b: Int, out: FloatArray) {
    val rf = r / 255f
    val gf = g / 255f
    val bf = b / 255f
    
    val max = max(rf, max(gf, bf))
    val min = min(rf, min(gf, bf))
    val deltaMaxMin = max - min
    
    var h: Float
    val s: Float
    val l = (max + min) / 2f
    if (max == min) {
        h = 0F
        s = 0F
    } else {
        h = when (max) {
            rf -> ((gf - bf) / deltaMaxMin) % 6f
            gf -> ((bf - rf) / deltaMaxMin) + 2f
            else -> ((rf - gf) / deltaMaxMin) + 4f
        }
        s = deltaMaxMin / (1f - abs(2f * l - 1f))
    }
    h = (h * 60f) % 360f
    if (h < 0) {
        h += 360f
    }
    out[0] = constrain(h, 360f)
    out[1] = constrain(s, 1f)
    out[2] = constrain(l, 1f)
}
private fun hsl2color(hsl: FloatArray): Int {
    val h = hsl[0]
    val s = hsl[1]
    val l = hsl[2]
    val c = (1f - abs(2 * l - 1f)) * s
    val m = l - 0.5f * c
    val x = c * (1f - abs(h / 60f % 2f - 1f))
    val hueSegment = h.toInt() / 60
    var r = 0
    var g = 0
    var b = 0
    when (hueSegment) {
        0 -> {
            r = (255 * (c + m)).roundToInt()
            g = (255 * (x + m)).roundToInt()
            b = (255 * m).roundToInt()
        }
        1 -> {
            r = (255 * (x + m)).roundToInt()
            g = (255 * (c + m)).roundToInt()
            b = (255 * m).roundToInt()
        }
        2 -> {
            r = (255 * m).roundToInt()
            g = (255 * (c + m)).roundToInt()
            b = (255 * (x + m)).roundToInt()
        }
        3 -> {
            r = (255 * m).roundToInt()
            g = (255 * (x + m)).roundToInt()
            b = (255 * (c + m)).roundToInt()
        }
        4 -> {
            r = (255 * (x + m)).roundToInt()
            g = (255 * m).roundToInt()
            b = (255 * (c + m)).roundToInt()
        }
        5, 6 -> {
            r = (255 * (c + m)).roundToInt()
            g = (255 * m).roundToInt()
            b = (255 * (x + m)).roundToInt()
        }
    }
    r = constrain(r)
    g = constrain(g)
    b = constrain(b)
    return Color.rgb(r, g, b)
}
private fun findContrastColor(color: Int, other: Int): Int {
    var fg = color
    if (calculateContrast(fg, other) >= MIN_RATIO) {
        return color
    }
    val lab = DoubleArray(3)
    color2LAB(fg, lab)
    var low = 0.toDouble()
    var high = lab[0]
    val a = lab[1]
    val b = lab[2]
    for (i in 0 .. 14) {
        if (high - low <= 0.00001)
            break
        val l = (low + high) / 2
        fg = lab2color(l, a, b)
        if (calculateContrast(fg, other) > MIN_RATIO) {
            low = l
        } else {
            high = l
        }
    }
    return lab2color(low, a, b)
}
private fun pivotXyzComponent(component: Double) =
    if (component > XYZ_EPSILON) component.pow(1 / 3.0) else (XYZ_KAPPA * component + 16) / 116
private fun constrain(amount: Int) = if (amount < 0) 0 else if (amount > 255) 255 else amount
private fun constrain(amount: Float, high: Float) = if (amount < 0F) 0F else if (amount > high) high else amount
private fun changeColorLightness(baseColor: Int, amount: Int): Int {
    color2LAB(baseColor, threadLocal.get()!!)
    threadLocal.get()!![0] = max(min(100.0, threadLocal.get()!![0] + amount), 0.0)
    return lab2color(threadLocal.get()!![0], threadLocal.get()!![1], threadLocal.get()!![2])
}