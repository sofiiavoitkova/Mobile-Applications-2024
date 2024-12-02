package com.example.create_account

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.accountcreate.R
import com.google.android.material.textfield.TextInputLayout

class CreateAccountActivity : AppCompatActivity() {

    private val loginLabel: TextView
        get() = findViewById(R.id.logInText)

    private val emailInputLayout: TextInputLayout
        get() = findViewById(R.id.emailInput)

    private val email: String
        get() = emailInputLayout.editText?.text?.toString().orEmpty()

    private val passwordInputLayout: TextInputLayout
        get() = findViewById(R.id.strongPasswordInput)

    private val password: String
        get() = passwordInputLayout.editText?.text?.toString().orEmpty()

    private val nextButton: Button
        get() = findViewById(R.id.nextButton)

    private val credentialsManager = CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        loginLabel.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
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
            register(email, password)
        }
    }

    private fun register(email: String, password: String) {
        val isRegistrationSuccessful = credentialsManager.register(email, password)

        if (!isRegistrationSuccessful) {
            Toast.makeText(
                this,
                "Email already registered.Try again with different email",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
