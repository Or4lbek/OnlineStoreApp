package com.example.onlinestoreapp.di

import com.example.onlinestoreapp.domain.use_case.ValidateEmailUseCase
import com.example.onlinestoreapp.domain.use_case.ValidateNameUseCase
import com.example.onlinestoreapp.domain.use_case.ValidatePasswordUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::ValidateEmailUseCase)
    single { ValidatePasswordUseCase() }
    single { ValidateNameUseCase() }
}