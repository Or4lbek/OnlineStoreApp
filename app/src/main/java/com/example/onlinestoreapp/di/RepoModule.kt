package com.example.onlinestoreapp.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.example.onlinestoreapp.domain.repository.OnlineStoreRepository
import com.example.onlinestoreapp.data.repository.OnlineStoreRepositoryImpl
import org.koin.dsl.bind

val repoModule = module {
    factoryOf(::OnlineStoreRepositoryImpl).bind(OnlineStoreRepository::class)
}