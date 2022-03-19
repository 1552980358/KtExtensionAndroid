package lib.github1552980358.ktExtension.android.content

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.io.Serializable

object Intent {
    
    /**
     * Create a new [Intent] instance with [block]
     **/
    @JvmStatic
    fun intent(block: Intent.(Intent) -> Unit) = Intent().apply{ block(this) }
    
    @JvmStatic
    fun intent(o: Intent, block: Intent.(Intent) -> Unit) = Intent(o).apply { block(this) }
    
    @JvmStatic
    fun intent(context: Context, clazz: Class<out Any>, block: Intent.(Intent) -> Unit) = Intent(context, clazz).apply { block(this) }
    
    @JvmStatic
    fun intent(action: String, block: Intent.(Intent) -> Unit) = Intent(action).apply{ block(this) }
    
    @JvmStatic
    fun intent(action: String, uri: Uri, block: Intent.(Intent) -> Unit) = Intent(action, uri).apply { block(this) }
    
    @JvmStatic
    fun intent(action: String, uri: Uri, context: Context, clazz: Class<out Any>, block: Intent.(Intent) -> Unit) =
        Intent(action, uri, context, clazz).apply { block(this) }
    
    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    fun <T: Serializable> Intent.getSerializableOf(name: String) = getSerializableExtra(name) as T?
    
}