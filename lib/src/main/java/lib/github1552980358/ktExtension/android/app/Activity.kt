package lib.github1552980358.ktExtension.android.app

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

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