@file:Suppress("unused")

package lib.github1552980358.ktExtension.android.java

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import lib.github1552980358.ktExtension.android.graphics.toDrawable

/**
 * Convert [ByteArray] into [Bitmap] using [BitmapFactory]
 **/
fun ByteArray.toBitmap(offset: Int = 0, length: Int = -1): Bitmap? {
    if (isEmpty()) {
        return null
    }
    if (offset < 0 || size < length) {
        return null
    }
    return BitmapFactory.decodeByteArray(
        this,
        offset,
        if (length == -1) this.size else length
    )
}

/**
 * Convert [ByteArray] into [Bitmap] using [BitmapFactory], [toBitmap] and [toDrawable]
 *
 * null will be returned if [ByteArray] is empty
 **/
fun ByteArray.toDrawable(context: Context): BitmapDrawable? {
    if (isEmpty()) {
        return null
    }
    
    return toBitmap().toDrawable(context.resources)
}

fun ByteArray.toDrawable(resources: Resources): BitmapDrawable? {
    if (isEmpty()) {
        return null
    }
    
    return toBitmap()?.toDrawable(resources)
}