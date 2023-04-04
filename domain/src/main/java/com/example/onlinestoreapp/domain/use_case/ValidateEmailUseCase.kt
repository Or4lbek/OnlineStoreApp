package com.example.onlinestoreapp.domain.use_case

import com.example.onlinestoreapp.core.R
import com.example.onlinestoreapp.core.UiText
import com.example.onlinestoreapp.domain.model.ValidationResult
import com.example.onlinestoreapp.domain.repository.EmailPatternValidator

class ValidateEmailUseCase(
    private val validator: EmailPatternValidator
) {
    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.email_blank_validation_message)
            )
        }
        if (!validator.isValidEmail(email)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.email_invalid_validation_message)
            )
        }
        return ValidationResult(successful = true)
    }
}