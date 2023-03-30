package com.example.onlinestoreapp.presentation.main.profile

import com.example.onlinestoreapp.domain.model.User


sealed class ProfileViewState {
    object ShowUserImageWasNotUpdated : ProfileViewState()
    object ShowUserImageSuccessfullyUpdated : ProfileViewState()

    data class OnUserFetched(val user: User?) : ProfileViewState()
}