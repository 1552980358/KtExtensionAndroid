@file:Suppress("unused")

package lib.github1552980358.ktExtension.android.java

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import lib.github1552980358.ktExtension.jvm.keyword.tryCatch
import java.io.InputStream

/**
 * Read [Bitmap] from [InputStream]
 **/
fun InputStream.readBitmap(): Bitmap? {
    var bitmap: Bitmap? = null
    tryCatch { bitmap = BitmapFactory.decodeStream(this) }
    close()
    return bitmap
}

/**
 * Read [Drawable] from [InputStream]
 **/
fun InputStream.readDrawable(srcName: String = "DRAWABLE"): Drawable? {
    var drawable: Drawable? = null
    tryCatch { drawable = Drawable.createFromStream(this, srcName) }
    close()
    return drawable
}