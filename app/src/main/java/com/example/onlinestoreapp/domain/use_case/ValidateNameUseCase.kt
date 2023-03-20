package com.example.onlinestoreapp.domain.use_case

import com.example.onlinestoreapp.domain.model.ValidationResult

class ValidateNameUseCase {
    operator fun invoke(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(successful = false, errorMessage = "The name can't be blank")
        }
        return ValidationResult(successful = true)
    }
}