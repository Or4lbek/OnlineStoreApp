package com.example.onlinestoreapp.domain.use_case

import android.util.Patterns
import com.example.onlinestoreapp.domain.model.ValidationResult

class ValidateEmailUseCase {
    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(successful = false, errorMessage = "The email can't be blank")
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(successful = false, errorMessage = "That's not valid email ")
        }
        return ValidationResult(successful = true)
    }
}