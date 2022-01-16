package lib.github1552980358.ktExtension.kotlinx.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

val ioScope get() = CoroutineScope(IO)

val mainScope get() = CoroutineScope(Main)

fun io(block: suspend CoroutineScope.() -> Unit) =
    ioScope.launch(block = block)

fun ui(block: suspend CoroutineScope.() -> Unit) =
    mainScope.launch(block = block)

fun CoroutineScope.io(block: suspend CoroutineScope.() -> Unit) =
    launch(IO, block = block)

fun CoroutineScope.ui(block: suspend CoroutineScope.() -> Unit) =
    launch(Main, block = block)

/**
 * Run try { ... } at [ioScope], catch { ... } (if specified with [CoroutineScope]) or/and
 * finally { ... } (if specified with [CoroutineScope])
 **/
fun ioTry(tryBlock: suspend CoroutineScope.() -> Unit) = CoroutineTry(ioScope, tryBlock)

/**
 * Run try { ... } at [mainScope], catch { ... } (if specified with [CoroutineScope]) or/and
 * finally { ... } (if specified with [CoroutineScope])
 **/
fun uiTry(tryBlock: suspend CoroutineScope.() -> Unit) = CoroutineTry(mainScope, tryBlock)

/**
 * Run try { ... } at current [CoroutineScope], catch { ... } (if specified with [CoroutineScope]) or/and
 * finally { ... } (if specified with [CoroutineScope])
 **/
fun CoroutineScope.launchTry(tryBlock: suspend CoroutineScope.() -> Unit) =
    CoroutineTry(this, tryBlock)