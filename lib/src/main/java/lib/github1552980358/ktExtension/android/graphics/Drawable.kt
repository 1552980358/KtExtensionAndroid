@file:Suppress("KDocUnresolvedReference")

package lib.github1552980358.ktExtension.android.graphics

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap

/**
 * androidx.core has provided official conversion method [Drawable.toBitmap].
 * Add
 * implementation 'androidx.core:core-ktx:1.3.0'
 * into dependencies of build.gradle, so that [Drawable.toBitmap]
 * is available for following converting method.
 **/
fun Drawable.toBitmap(): Bitmap? = toBitmap(intrinsicWidth, intrinsicHeight)

/** Convert into [ByteArray] **/
fun Drawable.toByteArray(
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
    quality: Int = 100
): ByteArray? = toBitmap().getByteArray(format, quality)
