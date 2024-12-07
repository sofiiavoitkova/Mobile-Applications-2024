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

    @Test
    fun givenUnusedEmail_whenCreateAccount_thenCreateAccount() {
        credentialsManager.register("ANOTHer@te.st", "12345")
        val isLoginSuccessful = credentialsManager.login("another@te.st", "12345")
        assertTrue(isLoginSuccessful)
    }

    @Test
    fun givenUsedEmail_whenCreateAccount_thenReturnError() {
        val firstRegistrationResult = credentialsManager.register("another@te.st", "12345")
        assertTrue(firstRegistrationResult)
        val secondRegistrationResult = credentialsManager.register("another@te.st", "12345")
        assertFalse(secondRegistrationResult)
    }

    @Test
    fun givenWrongCredentials_whenCreateAccount_thenReturnError() {
        val invalidEmailRegistrationResult = credentialsManager.register("abc.te.st", "12346")
        assertFalse(invalidEmailRegistrationResult)
    }

    @Test
    fun givenCaseDifferentEmails_whenCreateAccount_thenTreatAsSame() {
        val result1 = credentialsManager.register("test@te.st", "password")
        val result2 = credentialsManager.register("TEST@TE.st", "password")
        assertFalse(result2)
    }
}
