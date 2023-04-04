package com.example.onlinestoreapp.domain.presentation

import com.example.onlinestoreapp.core.UiText

sealed class ViewState<out T : Any> {
    data class Data<out T : Any>(val data: T) : ViewState<T>()
    object Loading : ViewState<Nothing>()
    object NetworkError : ViewState<Nothing>()
    data class Error(val error: UiText) : ViewState<Nothing>()
}