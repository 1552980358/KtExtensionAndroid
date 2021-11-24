@file:Suppress("unused")

package lib.github1552980358.ktExtension.android.content

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

/***********************************************************
 * fun example(context: Context) {                         *
 *     broadcastReceiver { context, intent, receiver ->    *
 *         intent?:return@broadcastReceiver                *
 *         when (intent.action) {                          *
 *             "ACTION1" -> { /** ... **/ }                *
 *             "ACTION2" -> { /** ... **/ }                *
 *         }                                               *
 *         context?.unregisterReceiver(receiver)           *
 *     }.register(context, arrayOf("ACTION1", "ACTION2"))  *
 * }                                                       *
 ***********************************************************/

/**
 * Creating a [BroadcastReceiver] object with overriding [BroadcastReceiver.onReceive] only
 **/
fun broadcastReceiver(block: (context: Context?, intent: Intent?, receiver: BroadcastReceiver) -> Unit) =
    object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            block(context, intent, this)
        }
    }

/**
 * Register [BroadcastReceiver]
 **/
fun BroadcastReceiver.register(context: Context, vararg actions: String) =
    context.registerBroadcastReceiver(this, actions)

fun BroadcastReceiver.register(context: Context, intentFilter: IntentFilter.() -> Unit) =
    context.registerBroadcastReceiver(this, intentFilter)

fun BroadcastReceiver.register(context: Context, action: String) =
    context.registerBroadcastReceiver(this, action)
