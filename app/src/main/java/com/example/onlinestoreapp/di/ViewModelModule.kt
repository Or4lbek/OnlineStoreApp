package com.example.onlinestoreapp.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.example.onlinestoreapp.presentation.authorization.registration.RegistrationViewModel
import com.example.onlinestoreapp.presentation.authorization.login.LoginViewModel

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegistrationViewModel)
}