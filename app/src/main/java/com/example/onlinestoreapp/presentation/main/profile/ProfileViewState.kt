package com.example.onlinestoreapp.presentation.main.profile

import com.example.onlinestoreapp.domain.model.UserAuth


sealed class ProfileViewState {
    object ShowUserImageWasNotUpdated : ProfileViewState()
    object ShowUserImageSuccessfullyUpdated : ProfileViewState()

    data class OnUserFetched(val userAuth: UserAuth?) : ProfileViewState()
}