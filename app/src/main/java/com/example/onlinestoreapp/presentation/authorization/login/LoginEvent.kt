package com.example.onlinestoreapp.presentation.authorization.login

sealed class LoginEvent {
    data class OnLoginClicked(val email: String, val password: String) : LoginEvent()
}