package com.example.onlinestoreapp.domain.presentation

sealed class AdvancedViewState<out T : Any> {
    data class Data<out T : Any>(val data: T) : AdvancedViewState<T>()
    object Loading : AdvancedViewState<Nothing>()
    object NetworkError : AdvancedViewState<Nothing>()
    data class Error(val error: String) : AdvancedViewState<Nothing>()
}