package com.example.onlinestoreapp.di

import com.example.onlinestoreapp.presentation.authorization.login.LoginViewModel
import com.example.onlinestoreapp.presentation.authorization.registration.RegistrationViewModel
import com.example.onlinestoreapp.presentation.main.detail.DetailViewModel
import com.example.onlinestoreapp.presentation.main.feed.FeedViewModel
import com.example.onlinestoreapp.presentation.main.profile.ProfileViewModel
import com.example.onlinestoreapp.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegistrationViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::FeedViewModel)
    viewModelOf(::DetailViewModel)
}