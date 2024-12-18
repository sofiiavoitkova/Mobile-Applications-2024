package com.example.create_account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.accountcreate.R
import com.google.android.material.textfield.TextInputLayout

class FragmentSignIn : Fragment() {

    private val credentialsManager = CredentialsManager

    private lateinit var registerNowLabel: TextView
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var nextButton: Button

    private val email: String
        get() = emailInputLayout.editText?.text?.toString().orEmpty()

    private val password: String
        get() = passwordInputLayout.editText?.text?.toString().orEmpty()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_sign_in, container, false)

        with(view) {
            registerNowLabel = findViewById(R.id.registerNowText)
            emailInputLayout = findViewById(R.id.emailInput)
            passwordInputLayout = findViewById(R.id.passwordInput)
            nextButton = findViewById(R.id.nextButton)
        }

        registerNowLabel.setOnClickListener {
            (activity as? FragmentsActivity)?.switchToRegister()
        }

        nextButton.setOnClickListener {
            handleLogin()
        }

        return view
    }

    private fun handleLogin() {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "All fields should be filled", Toast.LENGTH_SHORT).show()
            return
        }

        if (!credentialsManager.isEmailValid(email)) {
            emailInputLayout.error = "Invalid email format"
            return
        } else {
            emailInputLayout.isErrorEnabled = false
        }

        if (!credentialsManager.isPasswordValid(password)) {
            passwordInputLayout.error = "Invalid password format"
            return
        } else {
            passwordInputLayout.isErrorEnabled = false
        }

        login(email, password)
    }

    private fun login(email: String, password: String) {
        val isLoginSuccessful = credentialsManager.login(email, password)

        if (isLoginSuccessful) {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(context, "Error, Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }
}
