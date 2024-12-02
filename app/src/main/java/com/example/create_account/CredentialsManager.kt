package com.example.create_account

class CredentialsManager {

    private val map: MutableMap<String, String> = mutableMapOf()

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
        return regex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        val regex = Regex(passwordPattern)
        return regex.matches(password)
    }

    fun register(email: String, password: String): Boolean {
        val normalizedEmail = email.lowercase()

        if (!isEmailValid(email)) {
            return false
        }

        if (map.containsKey(normalizedEmail)) {
            return false
        }

        map[normalizedEmail] = password
        return true
    }

    fun login(email: String, password: String): Boolean {
        val normalizedEmail = email.lowercase()
        return map[normalizedEmail] == password
    }

    companion object {
        private val instance = CredentialsManager()

        fun isEmailValid(email: String): Boolean {
            return instance.isEmailValid(email)
        }

        fun isPasswordValid(password: String): Boolean {
            return instance.isPasswordValid(password)
        }

        fun login(email: String, password: String): Boolean {
            return instance.login(email, password)
        }

        fun register(email: String, password: String): Boolean {
            return instance.register(email, password)
        }
    }
}
