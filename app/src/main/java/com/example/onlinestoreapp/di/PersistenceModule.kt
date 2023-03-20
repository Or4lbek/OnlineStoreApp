package com.example.onlinestoreapp.di

import androidx.room.Room
import com.example.onlinestoreapp.data.db.AppDatabase
import com.example.onlinestoreapp.utils.Constants.USER_DB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val persistenceModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            USER_DB
        ).build()
    }

    single { get<AppDatabase>().onlineStoreDao() }
}


