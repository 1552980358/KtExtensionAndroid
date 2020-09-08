package lib.github1552980358.ktExtension.android.widget

import android.content.Context
import android.widget.Toast

/**
 * Quick call of [Toast.makeText] and show
 * @param length default as [Toast.LENGTH_SHORT]
 **/
fun toast(context: Context, message: CharSequence, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, message, length).show()

fun toast(context: Context, message: Int, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, message, length).show()

/**
 * Show short toast with text [message]
 **/
fun shortToast(context: Context, message: CharSequence) =
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

fun shortToast(context: Context, message: Int) =
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

/**
 * Show long toast with text [message]
 **/
fun longToast(context: Context, message: CharSequence) =
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()

fun longToast(context: Context, message: Int) =
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()