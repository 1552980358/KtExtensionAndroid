@file:Suppress("unused")

package lib.github1552980358.ktExtension.android.content

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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

/**
 * Register [BroadcastReceiver] without creating [IntentFilter] by hand
 **/
fun Context.registerBroadcastReceiver(broadcastReceiver: BroadcastReceiver, actions: Array<out String>) =
    registerReceiver(
        broadcastReceiver,
        IntentFilter().apply {
            actions.forEach { action ->
                addAction(action)
            }
        }
    )

/**
 * Call [Context.startActivity] with class [activity] and [intent].
 *
 * With 'intent: Intent.(Intent) -> Unit' method, two cases are provided
 * to have the [Intent] instance:
 *
 * Case 1: Directly use [this]
 *  Example:
 *      startActivity(activity) {
 *          // Call "this", we can get [Intent] instance
 *          putExtra(.., ..) // Or this.putExtra(.., ..)
 *      }
 *
 * Case 2: Custom [Intent] instance name
 *      startActivity(activity) { intent: Intent -> // Customizing [Intent] instance variable name in { ... }
 *                                                  // Type defining (:Intent) can be omitted
 *          // Call customized "intent" variable
 *          intent.putExtra(.., ..)
 *      }
 *
 **/
fun Context.startActivity(activity: Class<Activity>, intent: Intent.(Intent) -> Unit) =
    startActivity(Intent(this, activity).apply { intent(this) })

/**
 * Directly start an activity by calling [Context.startActivity] with class [activity]
 **/
fun Context.startActivity(activity: Class<Activity>) = startActivity(Intent(this, activity))