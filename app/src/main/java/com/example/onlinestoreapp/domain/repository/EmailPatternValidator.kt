package com.example.onlinestoreapp.domain.repository

interface EmailPatternValidator {
    fun isValidEmail(email: String): Boolean
}