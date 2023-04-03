package com.example.onlinestoreapp.presentation.login

sealed class LoginEvent {
    data class OnLoginClicked(val email: String, val password: String) : LoginEvent()
}