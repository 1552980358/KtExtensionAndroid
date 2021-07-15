@file:Suppress("unused")

package lib.github1552980358.ktExtension.android.content

import android.content.SharedPreferences

/**
 * Put [map] into [SharedPreferences.Editor]
 * @return [SharedPreferences.Editor]
 **/
fun SharedPreferences.Editor.putStrings(map: Map<String, String?>?): SharedPreferences.Editor = apply {
    map?.forEach { (name, value) -> putString(name, value) }
}

fun SharedPreferences.Editor.putBooleans(map: Map<String, Boolean>?): SharedPreferences.Editor = apply {
    map?.forEach { (name, value) -> putBoolean(name, value) }
}

fun SharedPreferences.Editor.putFloats(map: Map<String, Float>?): SharedPreferences.Editor = apply {
    map?.forEach { (name, value) -> putFloat(name, value) }
}

fun SharedPreferences.Editor.putInts(map: Map<String, Int>?): SharedPreferences.Editor = apply {
    map?.forEach { (name, value) -> putInt(name, value) }
}

fun SharedPreferences.Editor.putLongs(map: Map<String, Long>?): SharedPreferences.Editor = apply {
    map?.forEach { (name, value) -> putLong(name, value) }
}

fun SharedPreferences.Editor.putStringSets(map: Map<String, Set<String?>?>?): SharedPreferences.Editor = apply {
    map?.forEach { (name, value) -> putStringSet(name, value) }
}

/**
 * Put [map] into [SharedPreferences.Editor] and call [SharedPreferences.Editor.apply]
 * @return [Void]
 **/
fun SharedPreferences.putStringsApply(map: Map<String, String>) = edit().putStrings(map).apply()

fun SharedPreferences.putBooleansApply(map: Map<String, Boolean>) = edit().putBooleans(map).apply()

fun SharedPreferences.putFloatsApply(map: Map<String, Float>) = edit().putFloats(map).apply()

fun SharedPreferences.putIntApply(map: Map<String, Int>) = edit().putInts(map).apply()

fun SharedPreferences.putLongsApply(map: Map<String, Long>) = edit().putLongs(map).apply()

fun SharedPreferences.putStringSetsApply(map: Map<String, Set<String?>>) = edit().putStringSets(map).apply()

/**
 * Put [map] into [SharedPreferences.Editor] and call [SharedPreferences.Editor.apply]
 * @return [Int]
 **/
fun SharedPreferences.putStringsCommit(map: Map<String, String>) = edit().putStrings(map).commit()

fun SharedPreferences.putBooleansCommit(map: Map<String, Boolean>) = edit().putBooleans(map).commit()

fun SharedPreferences.putFloatsCommit(map: Map<String, Float>) = edit().putFloats(map).commit()

fun SharedPreferences.putIntCommit(map: Map<String, Int>) = edit().putInts(map).commit()

fun SharedPreferences.putLongsCommit(map: Map<String, Long>) = edit().putLongs(map).commit()

fun SharedPreferences.putStringSetsCommit(map: Map<String, Set<String?>>) = edit().putStringSets(map).commit()

/**
 * [SharedPreferences.Editor.commit] parameters [strings], [booleans], [floats], [ints], [longs], [stringSets] into [SharedPreferences]
 * @return [Boolean]
 **/
fun SharedPreferences.commitAll(strings: Map<String, String?>? = null, booleans: Map<String, Boolean>? = null,
                                floats: Map<String, Float>? = null, ints: Map<String, Int>? = null,
                                longs: Map<String, Long>? = null, stringSets: Map<String, Set<String?>?>? = null
) = edit()
    .putStrings(strings)
    .putBooleans(booleans)
    .putFloats(floats)
    .putInts(ints)
    .putLongs(longs)
    .putStringSets(stringSets)
    .commit()

/**
 * [SharedPreferences.Editor.apply] parameters [strings], [booleans], [floats], [ints], [longs], [stringSets] into [SharedPreferences]
 * @return [Void]
 **/
fun SharedPreferences.applyAll(strings: Map<String, String?>? = null, booleans: Map<String, Boolean>? = null,
                               floats: Map<String, Float>? = null, ints: Map<String, Int>? = null,
                               longs: Map<String, Long>? = null, stringSets: Map<String, Set<String?>?>? = null
) = edit()
    .putStrings(strings)
    .putBooleans(booleans)
    .putFloats(floats)
    .putInts(ints)
    .putLongs(longs)
    .putStringSets(stringSets)
    .apply()