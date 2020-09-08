package lib.github1552980358.ktExtension.android.content

import android.content.Context
import android.widget.Toast
import lib.github1552980358.ktExtension.android.content.res.getStatusBarHeight

/** Refer to [android.content.res.Resources.getStatusBarHeight] **/
fun Context.getStatusBarHeight() = resources.getStatusBarHeight()

fun Context.toast(message: CharSequence, length: Int = Toast.LENGTH_SHORT) =
    lib.github1552980358.ktExtension.android.widget.toast(this, message, length)

fun Context.toast(message: Int, length: Int = Toast.LENGTH_SHORT) =
    lib.github1552980358.ktExtension.android.widget.toast(this, message, length)

/**
 * Show short toast with text [message]
 **/
fun Context.shortToast(message: CharSequence) =
    lib.github1552980358.ktExtension.android.widget.shortToast(this, message)

fun Context.shortToast(message: Int) =
    lib.github1552980358.ktExtension.android.widget.shortToast(this, message)

/**
 * Show long toast with text [message]
 **/
fun Context.longToast(message: CharSequence) =
    lib.github1552980358.ktExtension.android.widget.longToast(this, message)

fun Context.longToast(message: Int) =
    lib.github1552980358.ktExtension.android.widget.longToast(this, message)