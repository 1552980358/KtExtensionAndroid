package lib.github1552980358.ktExtension.android.content

import android.content.Context
import kotlin.math.ceil

/** Return the height of status bar **/
fun Context.getStatusBarHeight() = try {
    // Try to reflect the system dimen id first
    resources.getIdentifier("status_bar_height", "dimen", "android").run {
        if (this > 0) {
            // Success, and return `int` value
            resources.getDimensionPixelSize(this)
        } else {
            // Return google official value, `25dp`
            ceil(25 * resources.displayMetrics.density).toInt()
        }
    }
} catch (e: Exception) {
    // Return google official value, `25dp`
    // if any exception thrown
    ceil(25 * resources.displayMetrics.density).toInt()
}
