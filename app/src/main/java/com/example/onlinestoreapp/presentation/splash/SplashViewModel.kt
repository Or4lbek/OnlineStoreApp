package com.example.onlinestoreapp.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlinestoreapp.domain.presentation.AdvancedViewState
import com.example.onlinestoreapp.domain.use_case.IsUserAuthUseCase

class SplashViewModel(
    private val isUserAuthUseCase: IsUserAuthUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<AdvancedViewState<SplashViewState>>()
    var viewState: LiveData<AdvancedViewState<SplashViewState>> = _viewState

    fun onSplashEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.OnUserEnter -> {
                isUserAuth()
            }
        }
    }

    private fun isUserAuth() {
        if (isUserAuthUseCase()) {
            _viewState.value = AdvancedViewState.Data(SplashViewState.UserWasAuthorized)
        } else {
            _viewState.value = AdvancedViewState.Data(SplashViewState.UserWasNotAuthorized)
        }
    }
}