package lib.github1552980358.ktExtension.android.content

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

fun broadcastReceiver(block: (context: Context?, intent: Intent?) -> Unit) =
    object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            block(context, intent)
        }
    }