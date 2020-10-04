package lib.github1552980358.ktExtension.androidx.fragment.app

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * Get Color with [resId]
 **/
fun Fragment.getColor(@StringRes resId: Int) = ContextCompat.getColor(requireContext(), resId)

/**
 * Get [Drawable] with [resId]
 **/
fun Fragment.getDrawable(@DrawableRes resId: Int) = requireContext().getDrawable(resId)

