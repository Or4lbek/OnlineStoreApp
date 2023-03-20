package com.example.onlinestoreapp.domain.use_case

import com.example.onlinestoreapp.domain.model.ValidationResult
import com.example.onlinestoreapp.domain.repository.EmailPatternValidator

class ValidateEmailUseCase(
    private val validator: EmailPatternValidator
) {
    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(successful = false, errorMessage = "The email can't be blank")
        }
        if (!validator.isValidEmail(email)) {
            return ValidationResult(successful = false, errorMessage = "That's not valid email ")
        }
        return ValidationResult(successful = true)
    }
}