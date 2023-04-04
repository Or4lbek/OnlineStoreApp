package com.example.onlinestoreapp.domain.model

import com.example.onlinestoreapp.core.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)