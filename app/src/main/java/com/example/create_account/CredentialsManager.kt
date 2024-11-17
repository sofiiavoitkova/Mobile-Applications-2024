package com.example.create_account

class CredentialsManager {
    private val emailPattern = (
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9\\-][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
            )

    private val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#\$%^&*()])(?=\\S+\$).{8,}\$"

    fun isEmailValid(email: String): Boolean {
        val regex = Regex(emailPattern)
        return email.isNotEmpty() && regex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        val regex = Regex(passwordPattern)
        return regex.matches(password)
    }
}
