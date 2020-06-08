@file:Suppress("unused")

package lib.github1552980358.ktExtension.android.kotlin

import java.lang.IllegalArgumentException
import java.util.Collections

/**
 * Swap element of index [i] and [j]
 * Same function as [Collections.swap]
 **/
fun <T> Array<T>.swap(i: Int, j: Int) {
    if (i == j) {
        // Do Nothing
        return
    }
    if (i < 0 || i >= size) {
        throw Exception("Out of range: Location $i, ArraySize: $size")
    }
    if (j < 0 || j >= size) {
        throw Exception("Out of range: Location $j, ArraySize: $size")
    }
    
    this[i].also { tmp ->
        this[i] = this[j]
        this[j] = tmp
    }
}

/**
 * Move element of index [i] into index [j]
 **/
fun <T> Array<T>.moveAndShift(i: Int, j: Int) {
    if (i == j) {
        return
    }
    if (i < 0 || i >= size) {
        throw IllegalArgumentException("Out of range: Location $i, ArraySize: $size")
    }
    if (j < 0 || j >= size) {
        throw IllegalArgumentException("Out of range: Location $j, ArraySize: $size")
    }
    
    // Shifting
    if (i > j) {
        this[i].also { temp ->
            for (index in j until i) {
                this[index + 1] = this[index]
            }
            this[j] = temp
        }
        return
    }
    
    this[i].also { temp ->
        for (index in i until j) {
            this[index] = this[index + 1]
        }
        this[j] = temp
    }
}
