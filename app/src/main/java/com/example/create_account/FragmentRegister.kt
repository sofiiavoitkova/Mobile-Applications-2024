package com.example.create_account

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

class FragmentRegister : Fragment() {

    private val credentialsManager = CredentialsManager

    private lateinit var loginLabel: TextView
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
        val view = inflater.inflate(R.layout.activity_create_account, container, false)

        with(view) {
            loginLabel = findViewById(R.id.logInText)
            emailInputLayout = findViewById(R.id.emailInput)
            passwordInputLayout = findViewById(R.id.strongPasswordInput)
            nextButton = findViewById(R.id.nextButton)
        }

        loginLabel.setOnClickListener {
            (activity as? FragmentsActivity)?.switchToLogin()
        }

        nextButton.setOnClickListener {
            handleRegistration()
        }

        return view
    }

    private fun handleRegistration() {
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

        register(email, password)
    }

    private fun register(email: String, password: String) {
        val isRegistrationSuccessful = credentialsManager.register(email, password)

        if (!isRegistrationSuccessful) {
            Toast.makeText(
                context,
                "Email already registered. Try again with a different email",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
            (activity as? FragmentsActivity)?.switchToLogin()
        }
    }
}
