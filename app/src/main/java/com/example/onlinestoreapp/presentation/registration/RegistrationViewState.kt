package com.example.onlinestoreapp.presentation.registration

sealed class RegistrationViewState {
    object ShowUserWasNotCreated : RegistrationViewState()
    object ShowUserSuccessfullyCreated : RegistrationViewState()
}