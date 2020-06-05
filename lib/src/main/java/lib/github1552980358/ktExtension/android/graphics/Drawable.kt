@file:Suppress("KDocUnresolvedReference")

package lib.github1552980358.ktExtension.android.graphics

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap

/**
 * toBitmap will not be coded and implemented, because androidx.core
 * has provided official conversion method [Drawable.toBitmap].
 * Add
 * implementation 'androidx.core:core-ktx:1.3.0'
 * into dependencies of build.gradle, so that [Drawable.toBitmap]
 * is available.
 **/

/** Convert into [ByteArray] **/
fun Drawable.toByteArray(
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
    quality: Int = 100
): ByteArray? {
    return toBitmap(intrinsicWidth, intrinsicHeight).getByteArray(format, quality)
}