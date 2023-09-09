package tat.neft.main.authorization

import android.widget.Toast

class logIn {

    private val r_login: String = "1"
    private val r_password: String = "1"

    fun logIn(l: String, p: String): Boolean {
        return l == r_login && p == r_password
    }

}