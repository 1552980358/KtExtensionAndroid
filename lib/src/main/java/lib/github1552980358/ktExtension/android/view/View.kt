@file:Suppress("HasPlatformType", "unused")

package lib.github1552980358.ktExtension.android.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 * Get Color Int with [resId] without calling [ContextCompat.getColor]
 **/
fun View.getColor(@ColorRes resId: Int) = ContextCompat.getColor(context, resId)

/**
 * Get Dimensions with [resId] without calling [Context.getResources]
 **/
fun View.getDimension(@DimenRes resId: Int) = context.resources.getDimension(resId)

fun View.getDimensionPixelOffset(@DimenRes resId: Int) = context.resources.getDimensionPixelOffset(resId)

fun View.getDimensionPixelSize(@DimenRes resId: Int) = context.resources.getDimensionPixelSize(resId)

/**
 * Return [DisplayMetrics] object without calling [Context.getResources]
 **/
fun View.getDisplayMetrics() = context.resources.displayMetrics

/**
 * Get [Drawable] with [resId] without calling [Context.getDrawable]
 **/
fun View.getDrawable(@DrawableRes resId: Int) = context.getDrawable(resId)

/**
 * Get [String] with [resId] without calling [Context.getString]
 **/
fun View.getString(@StringRes resId: Int) = context.getString(resId)

fun View.getString(@StringRes resId: Int, vararg formatArgs: Any) = context.getString(resId, formatArgs)

/**
 * Get [CharSequence] with [resId] without calling [Context.getText]
 **/
fun View.getText(@StringRes resId: Int) = context.getText(resId)
