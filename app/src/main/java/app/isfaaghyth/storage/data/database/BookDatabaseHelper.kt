package app.isfaaghyth.storage.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * 1. `create database` => SQLiteOpenHelper => books.db
 * 2. `create table` => onCreate() => db.execSQL(...)
 */
class BookDatabaseHelper constructor(
    context: Context
) : SQLiteOpenHelper(context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableSyntax = """
            CREATE TABLE ${BookContract.BookEntry.TABLE_NAME} 
            (${BookContract.BookEntry.COLUMN_ID} INTEGER PRIMARY KEY,
            ${BookContract.BookEntry.COLUMN_TITLE} TEXT,
            ${BookContract.BookEntry.COLUMN_AUTHOR} TEXT,
            ${BookContract.BookEntry.COLUMN_GENRE} TEXT,
            ${BookContract.BookEntry.COLUMN_PAGES} INT)
        """

        db?.execSQL(createTableSyntax)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVer: Int, newVer: Int) {
        val dropTableIfExist = "DROP TABLE IF EXISTS ${BookContract.BookEntry.TABLE_NAME}"
        db?.execSQL(dropTableIfExist)
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "books.db"
        const val DATABASE_VERSION = 1
    }
}