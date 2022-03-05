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
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import lib.github1552980358.ktExtension.android.app.restartActivity
import lib.github1552980358.ktExtension.android.content.isSystemDarkMode
import kotlin.reflect.full.createInstance

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

inline fun <reified dialog: DialogFragment> Fragment.showFragmentDialog() =
    (dialog::class.createInstance() as DialogFragment).show(parentFragmentManager, dialog::class.simpleName)

inline fun <reified dialogFragment: DialogFragment> dialogFragment.show(fragment: Fragment) =
    (this as DialogFragment).also { it.show(fragment.parentFragmentManager, dialogFragment::class.simpleName) }

/**
 * Check whether system is dark mode enabled
 **/
val Fragment.isSystemDarkMode get() = requireContext().isSystemDarkMode