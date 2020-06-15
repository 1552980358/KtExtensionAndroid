@file:Suppress("KDocUnresolvedReference")

package lib.github1552980358.ktExtension.android.graphics

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import lib.github1552980358.ktExtension.android.extension.ColorPalette
import lib.github1552980358.ktExtension.android.extension.getCalculate
import lib.github1552980358.ktExtension.jvm.keyword.tryCatch

/**
 * androidx.core has provided official conversion method [Drawable.toBitmap].
 * Add
 * implementation 'androidx.core:core-ktx:1.3.0'
 * into dependencies of build.gradle, so that [Drawable.toBitmap]
 * is available for following converting method.
 **/
fun Drawable.toBitmap(): Bitmap? {
    tryCatch { return toBitmap(intrinsicWidth, intrinsicHeight) }
    return null
}

/** Convert into [ByteArray] **/
fun Drawable.toByteArray(
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
    quality: Int = 100
): ByteArray? {
    tryCatch { return toBitmap().getByteArray(format, quality) }
    return null
}

/**
 * Get [ColorPalette] for media UI
 **/
fun Drawable.getColorPalette() = ColorPalette().getCalculate(this)