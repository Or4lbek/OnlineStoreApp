package com.example.onlinestoreapp.domain.network

import com.example.onlinestoreapp.core.UiText


sealed class Response<out T> {

    data class Success<out T>(val data: T, val message: String = "") : Response<T>()

    data class Error(val error: UiText) : Response<Nothing>()

    object NetworkError : Response<Nothing>()
}