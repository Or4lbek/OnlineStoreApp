package com.example.onlinestoreapp.presentation.login

sealed class LoginViewState {

    object ShowUserDoNotExist : LoginViewState()

    object OnUserFetched : LoginViewState()
}