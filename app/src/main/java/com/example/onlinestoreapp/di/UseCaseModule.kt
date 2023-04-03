package com.example.onlinestoreapp.di

import com.example.onlinestoreapp.domain.use_case.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::ValidateEmailUseCase)
    singleOf(::ValidatePasswordUseCase)
    singleOf(::ValidateNameUseCase)
    singleOf(::IsUserAuthUseCase)
    singleOf(::GetAuthTokenUseCase)
    singleOf(::CreateAuthTokenUseCase)
    singleOf(::RemoveAuthTokenUseCase)
}