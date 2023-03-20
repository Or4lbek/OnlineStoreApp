package com.example.onlinestoreapp.presentation.authorization.registration

sealed class RegistrationViewState {
    object ShowUserWasNotCreated : RegistrationViewState()
    object ShowUserSuccessfullyCreated : RegistrationViewState()
}