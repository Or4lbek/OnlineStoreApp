package com.example.onlinestoreapp.di

import com.example.onlinestoreapp.data.remote.StoreService
import com.example.onlinestoreapp.core.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val apiModule = module {
    singleOf(::provideHttpClient)
    singleOf(::provideStoreApiService)
}

fun provideHttpClient(): Retrofit {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return Retrofit.Builder()
        .client(
            OkHttpClient().newBuilder().addInterceptor(
                interceptor
            ).build()
        )
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


fun provideStoreApiService(retrofit: Retrofit): StoreService {
    return retrofit.create(StoreService::class.java)
}