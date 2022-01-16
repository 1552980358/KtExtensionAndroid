package lib.github1552980358.ktExtension.android.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

@Suppress("UNCHECKED_CAST")
fun <viewType: View> ViewGroup.inflateAs(@LayoutRes resId: Int, attachToRoot: Boolean = false): viewType =
    inflate(resId, attachToRoot) as viewType
 
fun ViewGroup.inflate(@LayoutRes resId: Int, attachToRoot: Boolean = false) =
    LayoutInflater.from(context).inflate(resId, this, attachToRoot)