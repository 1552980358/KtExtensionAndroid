package lib.github1552980358.ktExtension.android.database.sqlite

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Execute [sql] command and return [SQLiteDatabase] object
 **/
@Suppress("unused")
fun SQLiteOpenHelper.sqlExec(sql: String) = writableDatabase.sqlExec(sql)

/**
 * Execute [sql] commands and return [SQLiteDatabase] object
 **/
@Suppress("unused")
fun SQLiteOpenHelper.sqlExec(sql: Array<String>) = writableDatabase.sqlExec(sql)