@file:Suppress("unused")

package lib.github1552980358.ktExtension.android.graphics

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.BitmapDrawable
import androidx.core.graphics.drawable.toDrawable
import lib.github1552980358.ktExtension.android.extension.ColorPalette
import lib.github1552980358.ktExtension.jvm.io.osApply
import lib.github1552980358.ktExtension.jvm.keyword.tryCatch
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.abs

/** Output into file **/
fun Bitmap.writePNGToFile(file: File, quality: Int = 100) {
    file.outputStream().osApply {
        compress(Bitmap.CompressFormat.PNG, quality, this)
    }
}

fun Bitmap.writeJPEGToFile(file: File, quality: Int = 100) {
    file.outputStream().osApply {
        compress(Bitmap.CompressFormat.JPEG, quality, this)
    }
}

fun Bitmap.writeWEBPToFile(file: File, quality: Int = 100) {
    file.outputStream().osApply {
        compress(Bitmap.CompressFormat.WEBP, quality, this)
    }
}

/** Bitmap into drawable **/
fun Bitmap?.toDrawable(context: Context): BitmapDrawable? {
    this ?: return null
    return toDrawable(context.resources)
}

@Deprecated(
    "Use [Bitmap.toBitmap(Resources)] provided by androidx",
    ReplaceWith("Bitmap.toBitmap(Resources)", "androidx.core.graphics.drawable.Bitmap.toDrawable"),
    DeprecationLevel.ERROR
)
fun Bitmap?.toDrawable(resources: Resources): BitmapDrawable? {
    this ?: return null
    return BitmapDrawable(resources, this)
}

/**
 * get [ByteArray] from [Bitmap]
 **/
fun Bitmap.getByteArray(format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG, quality: Int = 100): ByteArray? {
    var byteArray: ByteArray? = null
    ByteArrayOutputStream().use { byteArrayOutputStream ->
        compress(format, quality, byteArrayOutputStream)
        tryCatch { byteArray = byteArrayOutputStream.toByteArray() }
    }
    return byteArray
}

fun Bitmap.copy(): Bitmap? = Bitmap.createBitmap(this)

/**
 * Cut image as square
 **/
fun Bitmap.cutSquare(): Bitmap? {
    if (width == 0 || height == 0) {
        return null
    }
    
    return when {
        width > height -> {
            Bitmap.createBitmap(this, (width - height) / 2, 0, width, width)
        }
        width < height -> {
            Bitmap.createBitmap(this, 0, (height - width) / 2, width, width)
        }
        else -> {
            this
        }
    }
    
}

fun Bitmap.cutSquare(centerX: Int, centerY: Int, sideLength: Int): Bitmap? {
    if (centerX <= 0 || centerX >= width) {
        return null
    }
    
    if (centerY <= 0 || centerX >= height) {
        return null
    }
    
    if (width > height && sideLength > height) {
        return null
    }
    
    return Bitmap.createBitmap(this, (centerX - sideLength) / 2, (centerY - sideLength) / 2, sideLength, sideLength)
}

/**
 * Cut image into circle
 **/
fun Bitmap.cutCircle(): Bitmap? {
    return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).also { canvasBitmap ->
        Canvas(canvasBitmap).also { canvas ->
            Paint().apply { isAntiAlias = true }.also { paint ->
                canvas.drawCircle(width / 2F, height / 2F, width / 2F, paint)
                paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
                canvas.drawBitmap(this, 0F, 0F, paint)
            }
        }
    }
}

fun Bitmap.cutCircle(centerX: Float, centerY: Float, radius: Float): Bitmap? {
    // At the edge
    if (centerX == 0F || centerX == width.toFloat() || centerY == 0F || centerY == height.toFloat()) {
        return null
    }
    
    // Diameter larger than size of Bitmap
    if (radius * 2 > width || radius * 2 > height) {
        return null
    }
    
    // Prevent radius exceeded
    if ((centerX + radius) > height || (abs(centerX - radius) > centerX)) {
        return null
    }
    if ((centerY + radius) > width || (abs(centerY - radius) > centerY)) {
        return null
    }
    
    // Cut into square
    return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).also { canvasBitmap ->
        Canvas(canvasBitmap).also { canvas ->
            Paint().apply { isAntiAlias = true }.also { paint ->
                canvas.drawCircle(centerX, centerY, radius, paint)
                paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
                canvas.drawBitmap(this, 0F, 0F, paint)
            }
        }
    }
}

/**
 * Get [ColorPalette] for media UI
 **/
fun Bitmap.getColorPalette(context: Context) = this.toDrawable(context)?.getColorPalette()

/**
 * [Bitmap.getWidth] into [Float] type
 **/
val Bitmap.widthF get() = width.toFloat()

/**
 * [Bitmap.getHeight] into [Float] type
 **/
val Bitmap.heightF get() = height.toFloat()