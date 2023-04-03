package com.example.onlinestoreapp.domain.use_case

import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.domain.model.ValidationResult

class ValidateNameUseCase {
    operator fun invoke(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.name_blank_validation_message
            )
        }
        return ValidationResult(successful = true)
    }
}