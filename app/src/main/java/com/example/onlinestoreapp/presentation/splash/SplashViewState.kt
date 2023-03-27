package com.example.onlinestoreapp.presentation.splash

sealed class SplashViewState {
    object UserWasAuthorized : SplashViewState()
    object UserWasNotAuthorized : SplashViewState()
}