package lib.github1552980358.ktExtension.android.view

import android.content.Context
import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.StringRes

/**
 * Get [String] with [resId] without calling [Context.getString]
 **/
fun View.getString(@StringRes resId: Int) = context.getString(resId)

fun View.getString(@StringRes resId: Int, vararg formatArgs: Any) = context.getString(resId, formatArgs)

/**
 * Get [CharSequence] with [resId] without calling [Context.getText]
 **/
fun View.getText(@StringRes resId: Int) = context.getText(resId)

/**
 * Get Dimensions with [resId] without calling [Context.getResources]
 **/
fun View.getDimension(@DimenRes resId: Int) = context.resources.getDimension(resId)

fun View.getDimensionPixelOffset(@DimenRes resId: Int) = context.resources.getDimensionPixelOffset(resId)

fun View.getDimensionPixelSize(@DimenRes resId: Int) = context.resources.getDimensionPixelSize(resId)
