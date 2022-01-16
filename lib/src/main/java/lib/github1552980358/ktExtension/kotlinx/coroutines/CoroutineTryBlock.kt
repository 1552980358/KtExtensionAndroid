package lib.github1552980358.ktExtension.kotlinx.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CoroutineTry(private val coroutineScope: CoroutineScope, private val block: suspend CoroutineScope.() -> Unit) {
    
    private var catchBlock: CoroutineCatch? = null
    private var finallyBlock: CoroutineFinally? = null
    
    /**
     * Set [catchBlock] to [ioScope]
     **/
    fun ioCatch(catchBlock: suspend CoroutineScope.(Exception) -> Unit) =
        setCatch(ioScope, catchBlock)
    
    /**
     * Set [catchBlock] to [mainScope]
     **/
    fun uiCatch(catchBlock: suspend CoroutineScope.(Exception) -> Unit) =
        setCatch(mainScope, catchBlock)
    
    /**
     * Set [catchBlock] to [coroutineScope]
     **/
    fun thenCatch(catchBlock: suspend CoroutineScope.(Exception) -> Unit) =
        setCatch(coroutineScope, catchBlock)
    
    /**
     * Set [catchBlock] to specified [CoroutineScope]
     **/
    fun setCatch(coroutineScope: CoroutineScope, catchBlock: suspend CoroutineScope.(Exception) -> Unit) = apply {
        this.catchBlock = CoroutineCatch(coroutineScope, catchBlock)
    }
    
    /**
     * Set [finallyBlock] to [ioScope]
     **/
    fun ioFinally(finallyBlock: suspend CoroutineScope.() -> Unit) =
        setFinally(ioScope, finallyBlock)
    
    /**
     * Set [finallyBlock] to [mainScope]
     **/
    fun uiFinally(finallyBlock: suspend CoroutineScope.() -> Unit) =
        setFinally(mainScope, finallyBlock)
    
    /**
     * Set [finallyBlock] to [coroutineScope]
     **/
    fun thenFinally(finallyBlock: suspend CoroutineScope.() -> Unit) =
        setFinally(coroutineScope, finallyBlock)
    
    /**
     * Set [finallyBlock] to specified [CoroutineScope]
     **/
    fun setFinally(coroutineScope: CoroutineScope, finallyBlock: suspend CoroutineScope.() -> Unit) = apply {
        this.finallyBlock = CoroutineFinally(coroutineScope, finallyBlock)
    }
    
    /**
     * Run [block] at [coroutineScope] with try { ... }
     */
    fun launch() = coroutineScope.launch {
        try {
            block(coroutineScope)
        } catch (e: Exception) {
            catchBlock?.onCatch(e)?.join()
        } finally {
            finallyBlock?.onFinally()?.join()
        }
    }
    
}

class CoroutineCatch(private val coroutineScope: CoroutineScope, private val block: suspend CoroutineScope.(Exception) -> Unit) {
    /**
     * Called at catch { ... }
     **/
    suspend fun onCatch(exception: Exception) = coroutineScope.launch { block(exception) }
}

class CoroutineFinally(private val coroutineScope: CoroutineScope, private val block: suspend CoroutineScope.() -> Unit) {
    /**
     * Called at finally { ... }
     **/
    fun onFinally() = coroutineScope.launch { block() }
    
}