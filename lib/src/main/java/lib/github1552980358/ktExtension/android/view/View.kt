@file:Suppress("HasPlatformType", "unused")

package lib.github1552980358.ktExtension.android.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewParent
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

/**
 * Get [View]'s [View.getWidth] and convert into [Float]
 **/
val View.widthF get() = width.toFloat()

/**
 * Get [View]'s [View.getHeight] and convert into [Float]
 **/
val View.heightF get() = height.toFloat()

/**
 * Call [ViewParent.rootParentOrChild] if [View.getParent] does not return null
 *
 * @return [ViewParent.rootParentOrChild] if [View.getParent] is not null
 * @return null when [View.getParent] returns null
 **/
val View.rootParent get(): ViewParent? = if (parent == null) null else parent.rootParentOrChild