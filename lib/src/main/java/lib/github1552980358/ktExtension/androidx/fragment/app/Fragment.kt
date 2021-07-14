package lib.github1552980358.ktExtension.androidx.fragment.app

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Get Color with [resId]
 **/
fun Fragment.getColor(@ColorRes resId: Int) = ContextCompat.getColor(requireContext(), resId)

/**
 * Get [Drawable] with [resId]
 **/
fun Fragment.getDrawable(@DrawableRes resId: Int) = requireContext().getDrawable(resId)

/**
 * Find a [V] typed [View] with [resId] from parent [FragmentActivity]
 **/
fun <V: View> Fragment.findActivityViewById(@IdRes resId: Int): V? = requireActivity().findViewById(resId)

/**
 * Get [F] typed [FragmentActivity] with [Fragment.requireActivity]
 **/
@Suppress("UNCHECKED_CAST")
fun <F: FragmentActivity> Fragment.requireTypedActivity() = requireActivity() as F