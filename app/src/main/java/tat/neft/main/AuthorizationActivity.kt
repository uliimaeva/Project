package tat.neft.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import tat.neft.R

class AuthorizationActivity : AppCompatActivity() {

    private lateinit var login_l: TextInputLayout
    private lateinit var login_et: TextInputEditText
    private lateinit var password_l: TextInputLayout
    private lateinit var password_et: TextInputEditText
    private lateinit var button: Button
    private var r_login: String = "1"
    private var r_password: String = "1"
    private var u_login: String = ""
    private var u_password: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        login_l = findViewById(R.id.TIL_login)
        login_et = findViewById(R.id.TIET_login)
        password_l = findViewById(R.id.TIL_password)
        password_et = findViewById(R.id.TIET_password)
        button = findViewById(R.id.authButton)

        button.setOnClickListener {

            u_login = login_et.text.toString()
            u_password = password_et.text.toString()

            when (logIn(u_login, u_password)) {
                true -> startActivity(Intent(this, TestActivity::class.java))
                false -> Toast.makeText(this, "smth wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun logIn(l: String, p: String): Boolean {
        login_l.isErrorEnabled = false
        password_l.isErrorEnabled = false
        if (l == "") {
            login_l.error = "Empty!"
            return false
        }
        if (p == "") {
            password_l.error = "Empty!"
            return false
        }
        if (l == r_login && p == r_password) {
            Toast.makeText(this, "Yapi!!", Toast.LENGTH_SHORT).show()
            return true
        } else {
            return false
        }

    }
}