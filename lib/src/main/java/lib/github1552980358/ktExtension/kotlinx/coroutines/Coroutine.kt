package lib.github1552980358.ktExtension.kotlinx.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

val ioDispatcher get() = CoroutineScope(IO)

val mainDispatcher get() = CoroutineScope(Main)

fun io(block: suspend CoroutineScope.() -> Unit) =
    ioDispatcher.launch(block = block)

fun ui(block: suspend CoroutineScope.() -> Unit) =
    mainDispatcher.launch(block = block)

fun CoroutineScope.io(block: suspend CoroutineScope.() -> Unit) =
    launch(IO, block = block)

fun CoroutineScope.ui(block: suspend CoroutineScope.() -> Unit) =
    launch(Main, block = block)