package com.example.onlinestoreapp.domain.network


sealed class Response<out T> {

    data class Success<out T>(val data: T, val message: String = "") : Response<T>()

    data class Error(val error: String) : Response<Nothing>()

    object NetworkError : Response<Nothing>()
}