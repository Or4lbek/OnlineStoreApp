package com.example.onlinestoreapp.di

import com.example.onlinestoreapp.data.repository.AndroidEmailPatternValidator
import com.example.onlinestoreapp.data.repository.AuthTokenRepositoryImpl
import com.example.onlinestoreapp.data.repository.AuthorizationRepositoryImpl
import com.example.onlinestoreapp.data.repository.OnlineStoreRepositoryImpl
import com.example.onlinestoreapp.domain.repository.AuthTokenRepository
import com.example.onlinestoreapp.domain.repository.AuthorizationRepository
import com.example.onlinestoreapp.domain.repository.EmailPatternValidator
import com.example.onlinestoreapp.domain.repository.OnlineStoreRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repoModule = module {
    factoryOf(::AuthorizationRepositoryImpl).bind(AuthorizationRepository::class)
    factoryOf(::AndroidEmailPatternValidator).bind(EmailPatternValidator::class)
    factoryOf(::AuthTokenRepositoryImpl).bind(AuthTokenRepository::class)
    factoryOf(::OnlineStoreRepositoryImpl).bind(OnlineStoreRepository::class)
}