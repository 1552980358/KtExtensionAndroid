@file:Suppress("unused")

package lib.github1552980358.ktExtension.android.graphics

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.core.graphics.drawable.toDrawable
import lib.github1552980358.ktExtension.jvm.javaKeyword.tryCatch
import lib.github1552980358.ktExtension.jvm.stream.osApply
import java.io.ByteArrayOutputStream
import java.io.File

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

