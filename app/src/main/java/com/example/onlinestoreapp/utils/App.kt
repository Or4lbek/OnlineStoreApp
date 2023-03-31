package com.example.onlinestoreapp.utils

import android.app.Application
import com.example.onlinestoreapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(persistenceModule)
            modules(repoModule)
            modules(viewModelModule)
            modules(useCaseModule)
            modules(apiModule)
            modules(mapperModule)
        }
    }
}