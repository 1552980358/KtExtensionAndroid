package lib.github1552980358.ktExtension.androidx.fragment.app

import android.app.Activity
import android.content.res.Resources
import android.content.res.Resources.Theme
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import lib.github1552980358.ktExtension.android.app.restartActivity

/**
 * Get Color with [resId]
 **/
fun Fragment.getColor(@ColorRes resId: Int) = ContextCompat.getColor(requireContext(), resId)

/**
 * Get [Drawable] with [resId]
 **/
fun Fragment.getDrawable(resId: Int, theme: Theme?) = ResourcesCompat.getDrawable(requireContext().resources, resId, theme)

/**
 * Get [Drawable] with [resId]
 **/
fun Fragment.getDrawable(@DrawableRes resId: Int) = getDrawable(resId, null)

/**
 * [Resources.getDimension]
 */
fun Fragment.dimensionOf(@DimenRes resId: Int) = resources.getDimension(resId)

/**
 * [Resources.getDimensionPixelSize]
 */
fun Fragment.pixelSizeOf(@DimenRes resId: Int) = resources.getDimensionPixelSize(resId)

/**
 * [Resources.getDimensionPixelOffset]
 */
fun Fragment.pixelOffsetOf(@DimenRes resId: Int) = resources.getDimensionPixelOffset(resId)

/**
 * Find a [V] typed [View] with [resId] from parent [FragmentActivity]
 **/
fun <V: View> Fragment.findActivityViewById(@IdRes resId: Int): V? = requireActivity().findViewById(resId)

/**
 * Get [F] typed [FragmentActivity] with [Fragment.requireActivity]
 **/
@Suppress("UNCHECKED_CAST")
fun <F: FragmentActivity> Fragment.requireTypedActivity() = requireActivity() as F

/**
 * Start a new [Fragment.requireActivity] instance and [Activity.finish] the old [Activity] instance
 **/
fun Fragment.restartActivity() = requireActivity().restartActivity()