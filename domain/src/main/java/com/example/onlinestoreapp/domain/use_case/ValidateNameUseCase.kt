package com.example.onlinestoreapp.domain.use_case

import com.example.onlinestoreapp.core.R
import com.example.onlinestoreapp.core.UiText
import com.example.onlinestoreapp.domain.model.ValidationResult

class ValidateNameUseCase {
    operator fun invoke(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.name_blank_validation_message)
            )
        }
        return ValidationResult(successful = true)
    }
}