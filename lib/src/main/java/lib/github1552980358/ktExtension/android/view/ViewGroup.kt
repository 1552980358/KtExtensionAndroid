package lib.github1552980358.ktExtension.android.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import lib.github1552980358.ktExtension.jvm.keyword.tryRun

@Suppress("UNCHECKED_CAST")
fun <viewType: View> ViewGroup.inflateAs(@LayoutRes resId: Int, attachToRoot: Boolean = false): viewType =
    inflate(resId, attachToRoot) as viewType
 
fun ViewGroup.inflate(@LayoutRes resId: Int, attachToRoot: Boolean = false) =
    LayoutInflater.from(context).inflate(resId, this, attachToRoot)

inline fun <reified viewHolder: ViewHolder> ViewGroup.createViewHolder(@LayoutRes resId: Int, attachToRoot: Boolean = false): viewHolder =
    viewHolder::class.constructors.find { it.parameters.size == 1 && it.parameters.first() == View::class.java }?.call(inflate(resId, attachToRoot)) ?: throw NoSuchMethodException()