package lib.github1552980358.ktExtension.android.view

import android.view.View
import androidx.annotation.StringRes

/**
 * Get string with [resId] without calling [Context].[getString]
 **/
fun View.getString(@StringRes resId: Int) = context.getString(resId)

fun View.getString(@StringRes resId: Int, vararg formatArgs: Any) = context.getString(resId, formatArgs)