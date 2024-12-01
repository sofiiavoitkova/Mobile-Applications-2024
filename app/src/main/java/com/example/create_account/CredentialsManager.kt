package com.example.create_account

class CredentialsManager {

    private val validEmail = "test@te.st"
    private val validPassword = "1234"

    private val emailPattern = (
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9\\-][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
            )

    private val passwordPattern = ".{4,}"

    fun isEmailValid(email: String): Boolean {
        val regex = Regex(emailPattern)
        return email.isNotEmpty() && regex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        val regex = Regex(passwordPattern)
        return regex.matches(password)
    }

    fun login(email: String, password: String): Boolean {
        return email == validEmail && password == validPassword
    }
}
