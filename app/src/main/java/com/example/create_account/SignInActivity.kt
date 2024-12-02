package com.example.create_account

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.accountcreate.R
import com.google.android.material.textfield.TextInputLayout

class SignInActivity : AppCompatActivity() {
    private val credentialsManager = CredentialsManager

    private val registerNowLabel: TextView
        get() = findViewById(R.id.registerNowText)

    private val emailInputLayout: TextInputLayout
        get() = findViewById(R.id.emailInput)

    private val email: String
        get() = emailInputLayout.editText?.text?.toString().orEmpty()

    private val passwordInputLayout: TextInputLayout
        get() = findViewById(R.id.passwordInput)

    private val password: String
        get() = passwordInputLayout.editText?.text?.toString().orEmpty()

    private val nextButton: Button
        get() = findViewById(R.id.nextButton)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        registerNowLabel.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        nextButton.setOnClickListener {
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "all fields should be filled", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!credentialsManager.isEmailValid(email)) {
                emailInputLayout.error = "Invalid email format"
                return@setOnClickListener
            } else {
                emailInputLayout.isErrorEnabled = false
            }
            if (!credentialsManager.isPasswordValid(password)) {
                passwordInputLayout.error = "Invalid password format"
                return@setOnClickListener
            } else {
                passwordInputLayout.isErrorEnabled = false
            }
            login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        val isLoginSuccessful = credentialsManager.login(email, password)

        if (isLoginSuccessful) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Error, Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }
}
