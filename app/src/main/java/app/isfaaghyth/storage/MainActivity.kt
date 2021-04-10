package app.isfaaghyth.storage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import app.isfaaghyth.storage.data.BookLocalManager
import app.isfaaghyth.storage.data.SessionManager

class MainActivity : AppCompatActivity() {

    private var edtUsername: EditText? = null
    private var btnLogin: Button? = null
    private var txtUsername: TextView? = null

    private val bookManager by lazy { BookLocalManager(this) }
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        castViewComponent()

        // instance the object of session manager
        sessionManager = SessionManager(this)

        // getting the data of username
        userProfile()

        btnLogin?.setOnClickListener {
            // getting the text we have input in edit text
            val username = edtUsername?.text?.toString()?: ""

            // storing the data into shared preferences
            sessionManager.storeUserSession(
                    username = username,
                    isLogin = true
            )

            // storing the book data
            bookManager.insertBook("Bangkit", "Isfa", "Workshop", 123)
            bookManager.insertBook("Tokopedia", "Ghiath", "Office-book", 456)

            // intent to bookListActivity
            startActivity(Intent(this, BookListActivity::class.java))
        }
    }

    private fun userProfile() {
        val isLogin = sessionManager.isLogin()
        if (isLogin) {
            txtUsername?.text = sessionManager.getUsername()
            startActivity(Intent(this, BookListActivity::class.java))
        } else {
            txtUsername?.text = "you're not login yet"
        }
    }

    private fun castViewComponent() {
        edtUsername = findViewById(R.id.edt_username)
        btnLogin = findViewById(R.id.btn_login)
        txtUsername = findViewById(R.id.txt_username)
    }

}