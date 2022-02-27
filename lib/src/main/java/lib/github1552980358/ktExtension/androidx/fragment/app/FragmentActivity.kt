package lib.github1552980358.ktExtension.androidx.fragment.app

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kotlin.reflect.full.createInstance

/**
 * Get current visible [Fragment] instance
 */
val FragmentActivity.currentChildFragment get() =
    supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.fragments?.first()

/**
 * Get current visible [Fragment] instance with [FragmentActivity.currentChildFragment] and
 * alias as [fragment]
 **/
fun <fragment: Fragment> FragmentActivity.currentChildFragment() = currentChildFragment as fragment?

inline fun <reified dialog: DialogFragment> FragmentActivity.showFragmentDialog() =
    (dialog::class.createInstance() as DialogFragment).show(supportFragmentManager, dialog::class.simpleName)