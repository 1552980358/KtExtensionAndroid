@file:Suppress("unused")

package lib.github1552980358.ktExtension.android.util

import android.util.Log

/**
 * Quick call of log output
 **/

fun logV(tag: String, msg: String) = Log.e(tag, msg)
fun logV(tag: String, msg: String, throwable: Throwable) = Log.e(tag, msg, throwable)

fun logD(tag: String, msg: String) = Log.d(tag, msg)
fun logD(tag: String, msg: String, throwable: Throwable) = Log.d(tag, msg, throwable)

fun logI(tag: String, msg: String) = Log.i(tag, msg)
fun logI(tag: String, msg: String, throwable: Throwable) = Log.i(tag, msg, throwable)

fun logW(tag: String, msg: String) = Log.w(tag, msg)
fun logW(tag: String, msg: String, throwable: Throwable) = Log.w(tag, msg, throwable)

fun logE(tag: String, msg: String) = Log.e(tag, msg)
fun logE(tag: String, msg: String, throwable: Throwable) = Log.e(tag, msg, throwable)

fun logWTF(tag: String, msg: String) = Log.wtf(tag, msg)
fun logWTF(tag: String, msg: String, throwable: Throwable) = Log.wtf(tag, msg, throwable)
