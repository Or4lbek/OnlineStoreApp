package com.example.onlinestoreapp.domain.model

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
) {
    override fun toString(): String {
        return "ValidationResult(successful=$successful, errorMessage=$errorMessage)"
    }
}