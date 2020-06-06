package lib.github1552980358.ktExtension.android.java

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import lib.github1552980358.ktExtension.android.graphics.toBitmap
import lib.github1552980358.ktExtension.android.graphics.writeJPEGToFile
import lib.github1552980358.ktExtension.android.graphics.writePNGToFile
import lib.github1552980358.ktExtension.android.graphics.writeWEBPToFile
import java.io.File

/**
 * Write [Bitmap] into [File]
 **/
fun File.writeBitmap(bitmap: Bitmap, compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG, quality: Int = 100) {
    when (compressFormat) {
        Bitmap.CompressFormat.PNG   -> bitmap.writePNGToFile(this, quality)
        Bitmap.CompressFormat.JPEG  -> bitmap.writeJPEGToFile(this, quality)
        Bitmap.CompressFormat.WEBP  -> bitmap.writeWEBPToFile(this, quality)
    }
}

fun File.writeDrawable(drawable: Drawable, compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG, quality: Int = 100) {
    when (compressFormat) {
        Bitmap.CompressFormat.PNG   -> drawable.toBitmap()?.writePNGToFile(this, quality)
        Bitmap.CompressFormat.JPEG  -> drawable.toBitmap()?.writeJPEGToFile(this, quality)
        Bitmap.CompressFormat.WEBP  -> drawable.toBitmap()?.writeWEBPToFile(this, quality)
    }
}