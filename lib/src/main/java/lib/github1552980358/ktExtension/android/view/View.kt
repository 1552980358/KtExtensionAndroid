package lib.github1552980358.ktExtension.android.view

import android.view.View
import androidx.annotation.StringRes

/**
 * Get [String] with [resId] without calling [Context].[getString]
 **/
fun View.getString(@StringRes resId: Int) = context.getString(resId)

fun View.getString(@StringRes resId: Int, vararg formatArgs: Any) = context.getString(resId, formatArgs)

/**
 * Get [CharSequence] with [resId] without calling [Context].[getText]
 **/
fun View.getText(@StringRes resId: Int) = context.getText(resId)
