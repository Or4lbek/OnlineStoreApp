package com.example.onlinestoreapp.data.repository

import android.util.Patterns
import com.example.onlinestoreapp.domain.repository.EmailPatternValidator

class AndroidEmailPatternValidator : EmailPatternValidator {
    override fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}