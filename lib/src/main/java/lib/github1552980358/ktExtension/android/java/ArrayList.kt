package lib.github1552980358.ktExtension.android.java

import java.util.Collections

/**
 * Copy all elements of an arrayList
 **/
fun <T> ArrayList<T>.copy() = ArrayList<T>().also { arrayList ->
    this.forEach { origin -> arrayList.add(origin) }
}

/**
 * Quick call of [Collections.swap]
 **/
fun <T> ArrayList<T>.swap(i: Int, j: Int) {
    Collections.swap(this, i, j)
}

/**
 * Copy a new [ArrayList] and [shuffle] content elements
 **/
fun <T> ArrayList<T>.copyAndShuffle() = copy().apply { shuffle() }

/**
 * Move element of index [i] into index [j]
 **/
fun <T> ArrayList<T>.moveAndShift(i: Int, j: Int) = add(j, removeAt(i))
