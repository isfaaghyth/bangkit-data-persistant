package app.isfaaghyth.storage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.isfaaghyth.storage.adapter.BookAdapter
import app.isfaaghyth.storage.data.BookLocalManager
import app.isfaaghyth.storage.data.database.BookContract

class BookListActivity : AppCompatActivity() {

    private var lstBook: RecyclerView? = null

    private val bookManager by lazy { BookLocalManager(this) }
    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        // casting the recyclerview
        lstBook = findViewById(R.id.lst_book)
        lstBook?.layoutManager = LinearLayoutManager(this)

        // instance the adapter
        adapter = BookAdapter(::onItemDeleted, bookManager.getBooks())

        // set the data into recyclerview's adapter
        lstBook?.adapter = adapter
    }

    fun addBook() {
        adapter.addBook(BookContract.Book())
    }

    private fun onItemDeleted(position: Int, book: BookContract.Book) {
        bookManager.deleteBook(book.author)
        adapter.deleteBook(position)
    }

}