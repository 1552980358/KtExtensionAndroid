package lib.github1552980358.ktExtension.androidx.coordinatorlayout.widget

import androidx.annotation.StringRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

/**
 * Short snackbar
 **/
fun CoordinatorLayout.shortSnack(@StringRes resId: Int) = makeSnack(resId, BaseTransientBottomBar.LENGTH_SHORT).show()
fun CoordinatorLayout.shortSnack(text: String) = makeSnack(text, BaseTransientBottomBar.LENGTH_SHORT).show()

/**
 * Long snackbar
 **/
fun CoordinatorLayout.longSnack(@StringRes resId: Int) = makeSnack(resId, BaseTransientBottomBar.LENGTH_LONG).show()
fun CoordinatorLayout.longSnack(text: String) = makeSnack(text, BaseTransientBottomBar.LENGTH_LONG).show()

/**
 * Make short snackbar
 **/
fun CoordinatorLayout.makeShortSnack(@StringRes resId: Int) = makeSnack(resId, BaseTransientBottomBar.LENGTH_SHORT)
fun CoordinatorLayout.makeShortSnack(text: String) = makeSnack(text, BaseTransientBottomBar.LENGTH_SHORT)

/**
 * Make long snackbar
 **/
fun CoordinatorLayout.makeLongSnack(@StringRes resId: Int) = makeSnack(resId, BaseTransientBottomBar.LENGTH_LONG)
fun CoordinatorLayout.makeLongSnack(text: String) = makeSnack(text, BaseTransientBottomBar.LENGTH_LONG)

/**
 * Snackbar with [resId]
 **/
fun CoordinatorLayout.makeSnack(@StringRes resId: Int, length: Int) = Snackbar.make(this, resId, length)

/**
 * Snackbar with [text]
 **/
fun CoordinatorLayout.makeSnack(text: String, length: Int) = Snackbar.make(this, text, length)