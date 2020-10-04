package lib.github1552980358.ktExtension.android.database.sqlite

import android.database.sqlite.SQLiteDatabase

/**
 * Execute [sql] command and return [SQLiteDatabase] object
 **/
@Suppress("unused")
fun SQLiteDatabase.sqlExec(sql: String) = apply {
    execSQL(sql)
}

/**
 * Execute [sql] commands and return [SQLiteDatabase] object
 **/
@Suppress("unused")
fun SQLiteDatabase.sqlExec(sql: Array<String>) = apply {
    sql.forEach { sqlLine -> execSQL(sqlLine) }
}