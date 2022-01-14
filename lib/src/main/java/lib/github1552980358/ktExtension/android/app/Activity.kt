@file:Suppress("unused")

package lib.github1552980358.ktExtension.android.app

import android.app.Activity
import android.content.pm.PackageManager
import android.content.res.Resources
import androidx.annotation.DimenRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import lib.github1552980358.ktExtension.android.content.startActivity

/**
 * Check whether permissions are granted.
 * [block] will be executed if all permissions are granted.
 * Otherwise, override [Activity.onRequestPermissionsResult] for following actions
 **/
fun Activity.checkAndQuestPermission(list: Array<String>, requestCode: Int = 100, block: () -> Unit) {
    val arrayList = ArrayList<String>()
    for (permission in list) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            continue
        }
        arrayList.add(permission)
    }
    if (arrayList.isEmpty()) {
        block()
        return
    }
    ActivityCompat.requestPermissions(this, arrayList.toTypedArray(), requestCode)
}

/**
 * Only check permissions, but not have any action after proved that all permissions are
 * granted
 **/
fun Activity.checkAndQuestPermission(list: Array<String>, requestCode: Int = 100) {
    val arrayList = ArrayList<String>()
    for (permission in list) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            continue
        }
        arrayList.add(permission)
    }
    if (arrayList.isEmpty()) {
        return
    }
    ActivityCompat.requestPermissions(this, arrayList.toTypedArray(), requestCode)
}

/**
 * Simply start a new [Activity.javaClass] instance and [Activity.finish] the old [Activity] instance
 **/
fun Activity.restartActivity() {
    startActivity(javaClass)
    finish()
}

/**
 * [Resources.getDimension]
 */
fun Activity.dimensionOf(@DimenRes resId: Int) = resources.getDimension(resId)

/**
 * [Resources.getDimensionPixelSize]
 */
fun Activity.pixelSizeOf(@DimenRes resId: Int) = resources.getDimensionPixelSize(resId)

/**
 * [Resources.getDimensionPixelOffset]
 */
fun Activity.pixelOffsetOf(@DimenRes resId: Int) = resources.getDimensionPixelOffset(resId)