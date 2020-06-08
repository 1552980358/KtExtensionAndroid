package lib.github1552980358.ktExtension.android.java

import java.io.File

/**
 * Execute command without calling [Runtime.getRuntime().exec]
 **/
fun execCmd(cmd: String) = Runtime.getRuntime().exec(cmd)

fun execCmd(array: Array<String>) = Runtime.getRuntime().exec(array)

fun execCmd(cmd: String, array: Array<String>) = Runtime.getRuntime().exec(cmd, array)

fun execCmd(array1: Array<String>, array2: Array<String>) = Runtime.getRuntime().exec(array1, array2)

fun execCmd(array1: Array<String>, array2: Array<String>, file: File) = Runtime.getRuntime().exec(array1, array2, file)

fun execCmd(cmd: String, array: Array<String>, file: File) = Runtime.getRuntime().exec(cmd, array, file)