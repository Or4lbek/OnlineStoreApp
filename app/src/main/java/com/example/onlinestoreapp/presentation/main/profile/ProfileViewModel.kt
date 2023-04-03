package com.example.onlinestoreapp.presentation.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.domain.presentation.ViewState
import com.example.onlinestoreapp.domain.repository.AuthorizationRepository
import com.example.onlinestoreapp.domain.use_case.GetAuthTokenUseCase
import com.example.onlinestoreapp.domain.use_case.RemoveAuthTokenUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: AuthorizationRepository,
    private val getAuthTokenUseCase: GetAuthTokenUseCase,
    private val removeAuthTokenUseCase: RemoveAuthTokenUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<ProfileViewState>>()
    var viewState: LiveData<ViewState<ProfileViewState>> = _viewState

    fun onProfileEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.OnUploadedItem -> {
                onUploadedItem(event.imageUri)
            }
            is ProfileEvent.OnUserLogOut -> {
                onUserLogOut()
            }
            is ProfileEvent.OnScreenOpen -> {
                onScreenOpen()
            }
        }
    }

    private fun onUploadedItem(imageUri: String) {
        viewModelScope.launch {
            val updatedUser = repository.getUserByTokenId(getAuthTokenUseCase())
            if (updatedUser != null) {
                updatedUser.image = imageUri
                repository.createUser(updatedUser)
                _viewState.value = ViewState.Data(
                    ProfileViewState.ShowUserImageSuccessfullyUpdated
                )
            } else {
                _viewState.value = ViewState.Data(
                    ProfileViewState.ShowUserImageWasNotUpdated
                )
            }
        }
    }

    private fun onScreenOpen() {
        viewModelScope.launch {
            val user = repository.getUserByTokenId(getAuthTokenUseCase())
            _viewState.value = ViewState.Data(ProfileViewState.OnUserFetched(user))
        }
    }

    private fun onUserLogOut() {
        removeAuthTokenUseCase()
    }
}