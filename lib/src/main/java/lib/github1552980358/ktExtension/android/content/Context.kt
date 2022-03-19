@file:Suppress("unused")

package lib.github1552980358.ktExtension.android.content

import android.app.Activity
import android.app.Service
import android.app.UiModeManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.UI_MODE_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import android.widget.Toast
import androidx.core.app.ActivityCompat
import lib.github1552980358.ktExtension.android.content.Intent.intent
import lib.github1552980358.ktExtension.android.content.res.getStatusBarHeight

object Context {
    
    /** Refer to [android.content.res.Resources.getStatusBarHeight] **/
    @JvmStatic
    fun Context.getStatusBarHeight() = resources.getStatusBarHeight()
    
    @JvmStatic
    fun Context.toast(message: CharSequence, length: Int = Toast.LENGTH_SHORT) =
        lib.github1552980358.ktExtension.android.widget.toast(this, message, length)
    
    @JvmStatic
    fun Context.toast(message: Int, length: Int = Toast.LENGTH_SHORT) =
        lib.github1552980358.ktExtension.android.widget.toast(this, message, length)
    
    /**
     * Show short toast with text [message]
     **/
    @JvmStatic
    fun Context.shortToast(message: CharSequence) =
        lib.github1552980358.ktExtension.android.widget.shortToast(this, message)
    
    @JvmStatic
    fun Context.shortToast(message: Int) =
        lib.github1552980358.ktExtension.android.widget.shortToast(this, message)
    
    /**
     * Show long toast with text [message]
     **/
    @JvmStatic
    fun Context.longToast(message: CharSequence) =
        lib.github1552980358.ktExtension.android.widget.longToast(this, message)
    
    @JvmStatic
    fun Context.longToast(message: Int) =
        lib.github1552980358.ktExtension.android.widget.longToast(this, message)
    
    /**
     * Register [BroadcastReceiver] without creating [IntentFilter] by hand
     **/
    @JvmStatic
    fun Context.registerBroadcastReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter.() -> Unit) =
        registerReceiver(broadcastReceiver, IntentFilter().apply(intentFilter))
    
    @JvmStatic
    fun Context.registerBroadcastReceiver(broadcastReceiver: BroadcastReceiver, action: String) =
        registerReceiver(broadcastReceiver, IntentFilter(action))
    
    @JvmStatic
    fun Context.registerBroadcastReceiver(broadcastReceiver: BroadcastReceiver, actions: Array<out String>) =
        registerBroadcastReceiver(broadcastReceiver) {
            actions.forEach { action -> addAction(action) }
        }
    
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
    @JvmStatic
    fun Context.startActivity(activity: Class<Activity>, intent: Intent.(Intent) -> Unit) =
        startActivity(Intent(this, activity).apply { intent(this) })
    
    /**
     * Directly start an activity by calling [Context.startActivity] with class [activity]
     **/
    @JvmStatic
    fun Context.startActivity(activity: Class<Activity>) = startActivity(Intent(this, activity))
    
    /**
     * Check [permission]
     * @return [Boolean]
     *      1. [true] if all permissions are gained;
     *      2. [false] if some / all permissions are not gained
     **/
    @JvmStatic
    fun Context.checkPermission(permission: String) = ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    
    /**
     * Check [permissions]
     * @param permissions
     * @return same as [Context.checkPermission]
     **/
    @JvmStatic
    fun Context.checkPermissionList(permissions: List<String>): Boolean {
        for (permission in permissions) {
            if (!checkPermission(permission)) {
                return false
            }
        }
        return true
    }
    
    @JvmStatic
    fun Context.checkPermissions(vararg permissions: String) = checkPermissionList(permissions.toList())
    
    @JvmStatic
    fun Context.checkPermissionArray(permissions: Array<String>) = checkPermissionList(permissions.toList())
    
    @JvmStatic
    inline fun <reified T: Activity> Context.startActivityOf(intent: Intent.() -> Unit = {}) =
        startActivity(Intent(this, T::class.java).apply(intent))
    
    @JvmStatic
    inline fun <reified T: Service> Context.startServiceOf(intent: Intent.() -> Unit = {}) = Intent(this, T::class.java).apply(intent).apply {
        when {
            SDK_INT < O -> startService(this)
            else -> startForegroundService(this)
        }
    }
    
    @JvmStatic
    fun Context.sendBroadcast(action: String, block: Intent.() -> Unit = {}) =
        sendBroadcast(intent(action) { block() })
    
    /**
     * Check whether system is dark mode enabled
     **/
    @JvmStatic
    val Context.isSystemDarkMode get() = (getSystemService(UI_MODE_SERVICE) as UiModeManager).nightMode == UiModeManager.MODE_NIGHT_YES
    
}