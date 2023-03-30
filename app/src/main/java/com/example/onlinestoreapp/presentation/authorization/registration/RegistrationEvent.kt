package com.example.onlinestoreapp.presentation.authorization.registration

import com.example.onlinestoreapp.domain.model.User

sealed class RegistrationEvent {
    data class OnRegistrationClicked(val user: User) : RegistrationEvent()
}