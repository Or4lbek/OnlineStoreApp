package com.example.onlinestoreapp.presentation.authorization.login

import com.example.onlinestoreapp.domain.model.UserAuth

sealed class LoginViewState {

    object ShowUserDoNotExist : LoginViewState()

    data class OnUserFetched(val user: UserAuth) : LoginViewState()
}