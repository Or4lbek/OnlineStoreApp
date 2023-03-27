package com.example.onlinestoreapp.presentation.authorization.login

sealed class LoginViewState {

    object ShowUserDoNotExist : LoginViewState()

    object OnUserFetched : LoginViewState()
}