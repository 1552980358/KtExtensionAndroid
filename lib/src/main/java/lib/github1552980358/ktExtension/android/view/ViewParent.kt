package lib.github1552980358.ktExtension.android.view

import android.view.View
import android.view.ViewParent

/**
 * Check whether [ViewParent.getParent] exists
 **/
val ViewParent.hasParent get() = parent != null

/**
 * Get the root [ViewParent] by [ViewParent.getParent] until
 * [View.getParent] return null
 *
 * @return [ViewParent]
 * @return null if the parent equals this instance
 **/
val ViewParent.rootParent get(): ViewParent? {
    var tmp = this
    while (tmp.hasParent) {
        tmp = tmp.parent
    }
    return if (this == tmp) { null } else { tmp }
}

/**
 * Call [ViewParent.rootParent]
 * @return [ViewParent] itself instance or the root [ViewParent]
 **/
val ViewParent.rootParentOrChild get() = rootParent ?: this