package com.example.onlinestoreapp.presentation.authorization.registration

import com.example.onlinestoreapp.domain.model.UserAuth

sealed class RegistrationEvent {
    data class OnRegistrationClicked(val userAuth: UserAuth) : RegistrationEvent()
}