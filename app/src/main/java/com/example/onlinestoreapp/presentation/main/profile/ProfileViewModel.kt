package com.example.onlinestoreapp.presentation.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.domain.presentation.AdvancedViewState
import com.example.onlinestoreapp.domain.repository.OnlineStoreRepository
import com.example.onlinestoreapp.domain.use_case.GetAuthTokenUseCase
import com.example.onlinestoreapp.domain.use_case.RemoveAuthTokenUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: OnlineStoreRepository,
    private val getAuthTokenUseCase: GetAuthTokenUseCase,
    private val removeAuthTokenUseCase: RemoveAuthTokenUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<AdvancedViewState<ProfileViewState>>()
    var viewState: LiveData<AdvancedViewState<ProfileViewState>> = _viewState

    fun onProfileEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.OnUploadedItem -> {
                onUploadedItem(event.imageUri)
            }
            is ProfileEvent.OnUserLogOut -> {
                onUserLogOut()
            }
            is ProfileEvent.OnUserOpen -> {
                onUserOpen()
            }
        }
    }

    private fun onUploadedItem(imageUri: String) {
        viewModelScope.launch {
            val updatedUser = repository.getUserByTokenId(getAuthTokenUseCase())
            if (updatedUser != null) {
                updatedUser.image = imageUri
                repository.createUser(updatedUser)
                _viewState.value = AdvancedViewState.Data(
                    ProfileViewState.ShowUserImageSuccessfullyUpdated
                )
            } else {
                _viewState.value = AdvancedViewState.Data(
                    ProfileViewState.ShowUserImageWasNotUpdated
                )
            }
        }
    }

    private fun onUserOpen() {
        viewModelScope.launch {
            println("userToken: " + getAuthTokenUseCase())
            val user = repository.getUserByTokenId(getAuthTokenUseCase())
            _viewState.value = AdvancedViewState.Data(ProfileViewState.OnUserFetched(user))
        }
    }

    private fun onUserLogOut() {
        removeAuthTokenUseCase()
    }
}