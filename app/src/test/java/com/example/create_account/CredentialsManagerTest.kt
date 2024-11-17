package com.example.create_account

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class CredentialsManagerTest {
    private val credentialsManager = CredentialsManager()

    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val isEmailValid = credentialsManager.isEmailValid("")
        assertEquals(false, isEmailValid)
    }

    @Test
    fun givenWrongEmailFormat_thenReturnFalse() {
        val isEmailValid = credentialsManager.isEmailValid("wrongEmailFormat")
        assertEquals(false, isEmailValid)
    }

    @Test
    fun givenProperEmailFormat_thenReturnTrue() {
        val isEmailValid = credentialsManager.isEmailValid("test@gmail.com")
        assertEquals(true, isEmailValid)
    }

    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val isPasswordValid = credentialsManager.isPasswordValid("")
        assertEquals(false, isPasswordValid)
    }

    @Test
    fun givenFilledPassword_thenReturnTrue() {
        val isPasswordValid = credentialsManager.isPasswordValid("!Passwd0")
        assertEquals(true, isPasswordValid)
    }
}