package com.example.onlinestoreapp.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlinestoreapp.domain.presentation.ViewState
import com.example.onlinestoreapp.domain.use_case.IsUserAuthUseCase

class SplashViewModel(
    private val isUserAuthUseCase: IsUserAuthUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<SplashViewState>>()
    var viewState: LiveData<ViewState<SplashViewState>> = _viewState

    fun onSplashEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.OnUserEnter -> {
                isUserAuth()
            }
        }
    }

    private fun isUserAuth() {
        if (isUserAuthUseCase()) {
            _viewState.value = ViewState.Data(SplashViewState.UserWasAuthorized)
        } else {
            _viewState.value = ViewState.Data(SplashViewState.UserWasNotAuthorized)
        }
    }
}