package app.isfaaghyth.storage.data

import android.content.Context
import android.content.SharedPreferences

class SessionManager(private val context: Context) {

    // first, we need to define the shared preferences name
    private val prefName = "session_manager"

    // second, we need to preparing the object of shared preferences
    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    // storing the data of username and isLogin flag
    fun storeUserSession(username: String, isLogin: Boolean) {
        val editor = sharedPref.edit()
        editor.putString(KEY_USERNAME, username)
        editor.putBoolean(KEY_IS_LOGIN, isLogin)
        editor.apply()
    }

    // getting the username data
    fun getUsername(): String {
        return sharedPref.getString(KEY_USERNAME, "")?: ""
    }

    // getting the flag of login
    fun isLogin(): Boolean {
        return sharedPref.getBoolean(KEY_IS_LOGIN, false)
    }

    companion object {
        const val KEY_USERNAME = "username"
        const val KEY_IS_LOGIN = "is_login"
    }

}