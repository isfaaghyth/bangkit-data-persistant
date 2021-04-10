package app.isfaaghyth.storage.data.database

import android.provider.BaseColumns

class BookContract {
    data class Book(
        var id: Int = 0,
        var title: String = "",
        var author: String = "",
        var genre: String = "",
        var pages: Int = 0
    )

    class BookEntry : BaseColumns {
        companion object {
            // table name
            const val TABLE_NAME = "fav_book"

            // columns that contains on `fav_book` TABLE
            const val COLUMN_ID = "_id"
            const val COLUMN_TITLE = "title"
            const val COLUMN_AUTHOR = "author"
            const val COLUMN_GENRE = "genre"
            const val COLUMN_PAGES = "pages"
        }
    }
}