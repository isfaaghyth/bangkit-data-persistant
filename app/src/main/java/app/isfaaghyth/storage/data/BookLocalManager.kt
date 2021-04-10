package app.isfaaghyth.storage.data

import android.content.ContentValues
import android.content.Context
import app.isfaaghyth.storage.data.database.BookContract
import app.isfaaghyth.storage.data.database.BookDatabaseHelper

class BookLocalManager constructor(
    private val context: Context
) {

    // first, instance the book database helper
    private val dbHelper by lazy { BookDatabaseHelper(context) }

    // second, getting the database access
    private val writeableDb = dbHelper.writableDatabase
    private val readableDb = dbHelper.readableDatabase

    fun insertBook(title: String, author: String, genre: String, pages: Int) {
        // define the content values to storing the data into sqlite
        val values = ContentValues()
        values.put(BookContract.BookEntry.COLUMN_TITLE, title)
        values.put(BookContract.BookEntry.COLUMN_AUTHOR, author)
        values.put(BookContract.BookEntry.COLUMN_GENRE, genre)
        values.put(BookContract.BookEntry.COLUMN_PAGES, pages)

        writeableDb.insert(
            BookContract.BookEntry.TABLE_NAME,
            null,
            values
        )
    }

    fun deleteBook(author: String) {
        val selection = BookContract.BookEntry.COLUMN_AUTHOR + " LIKE ?"
        val selectionArgs = arrayOf(author)

        readableDb.delete(
            BookContract.BookEntry.TABLE_NAME,
            selection,
            selectionArgs
        )
    }

    fun getBooks(): MutableList<BookContract.Book> {
        // define the temporary list of book variable
        val books = mutableListOf<BookContract.Book>()

        // define the cursor to representing of the data list
        readableDb.query(
            false,
            BookContract.BookEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(BookContract.BookEntry.COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(BookContract.BookEntry.COLUMN_TITLE))
                val author = getString(getColumnIndexOrThrow(BookContract.BookEntry.COLUMN_AUTHOR))
                val genre = getString(getColumnIndexOrThrow(BookContract.BookEntry.COLUMN_GENRE))
                val pages = getInt(getColumnIndexOrThrow(BookContract.BookEntry.COLUMN_PAGES))

                // insert into books
                books.add(
                    BookContract.Book(
                    id,
                    title,
                    author,
                    genre,
                    pages
                ))
            }
        }

        return books
    }

}