package com.example.onlinestoreapp.di

import android.content.Context
import androidx.room.Room
import com.example.onlinestoreapp.data.local.AppDatabase
import com.example.onlinestoreapp.data.local.AuthorizationDao
import com.example.onlinestoreapp.utils.Constants.USER_DB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


fun provideAppDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        USER_DB
    ).build()
}

fun provideOnlineStoreDao(database: AppDatabase): AuthorizationDao {
    return database.onlineStoreDao()
}

val persistenceModule = module {
    single { provideAppDatabase(androidContext()) }
    single { provideOnlineStoreDao(get()) }
}


