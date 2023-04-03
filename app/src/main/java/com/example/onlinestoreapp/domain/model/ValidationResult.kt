package com.example.onlinestoreapp.domain.model

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: Int? = null
)