package com.example.onlinestoreapp.presentation.registration

import com.example.onlinestoreapp.domain.model.User

sealed class RegistrationEvent {
    data class OnRegistrationClicked(val user: User) : RegistrationEvent()
}