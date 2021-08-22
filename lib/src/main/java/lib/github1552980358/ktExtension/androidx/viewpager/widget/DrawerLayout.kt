package lib.github1552980358.ktExtension.androidx.viewpager.widget

import androidx.annotation.StringRes
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar

/**
 * Short snackbar
 **/
fun DrawerLayout.shortSnack(@StringRes resId: Int) = makeSnack(resId, LENGTH_SHORT)
fun DrawerLayout.shortSnack(text: String) = makeSnack(text, LENGTH_SHORT)

/**
 * Long snackbar
 **/
fun DrawerLayout.longSnack(@StringRes resId: Int) = makeSnack(resId, LENGTH_LONG)
fun DrawerLayout.longSnack(text: String) = makeSnack(text, LENGTH_LONG)

/**
 * Snackbar with [resId]
 **/
fun DrawerLayout.makeSnack(@StringRes resId: Int, length: Int) = Snackbar.make(this, resId, length)

/**
 * Snackbar with [text]
 **/
fun DrawerLayout.makeSnack(text: String, length: Int) = Snackbar.make(this, text, length)