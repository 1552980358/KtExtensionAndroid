package lib.github1552980358.ktExtension.kotlinx.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class CoroutineBlock(protected val coroutineScope: CoroutineScope, protected val block: suspend CoroutineScope.() -> Unit) {
    /**
     * Method to be called for running
     **/
    abstract suspend fun runBlock()
}

class CoroutineTry(coroutineScope: CoroutineScope, block: suspend CoroutineScope.() -> Unit)
    : CoroutineBlock(coroutineScope, block) {
    
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
    fun mainCatch(catchBlock: suspend CoroutineScope.(Exception) -> Unit) =
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
    fun mainFinally(finallyBlock: suspend CoroutineScope.() -> Unit) =
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
     * Run [runBlock] at [coroutineScope]
     */
    fun launch() = coroutineScope.launch { runBlock() }
    
    /**
     * Run block, with specified content
     */
    override suspend fun runBlock() {
        try {
            block(coroutineScope)
        } catch (e: Exception) {
            catchBlock?.onCatch(e)
        } finally {
            finallyBlock?.runBlock()
        }
    }
    
}

class CoroutineCatch(private val coroutineScope: CoroutineScope, private val block: suspend CoroutineScope.(Exception) -> Unit) {
    /**
     * Called at catch { ... }
     **/
    suspend fun onCatch(exception: Exception) = coroutineScope.launch { block(exception) }
}

class CoroutineFinally(coroutineScope: CoroutineScope, block: suspend CoroutineScope.() -> Unit)
    : CoroutineBlock(coroutineScope, block) {
    /**
     * Called at finally { ... }
     **/
    override suspend fun runBlock() {
        coroutineScope.launch { block() }
    }
}