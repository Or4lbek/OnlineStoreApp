package com.example.onlinestoreapp.domain.use_case

import com.example.onlinestoreapp.core.R
import com.example.onlinestoreapp.core.UiText
import com.example.onlinestoreapp.domain.model.ValidationResult

class ValidatePasswordUseCase {
    operator fun invoke(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.password_length_validation_message)
            )
        }
        val containsLettersAndDigits =
            password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.password_validation_message)
            )
        }
        return ValidationResult(successful = true)
    }
}