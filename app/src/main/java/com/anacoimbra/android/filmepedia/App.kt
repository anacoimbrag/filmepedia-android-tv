package com.anacoimbra.android.filmepedia

import android.app.Application
import com.anacoimbra.android.filmepedia.api.networkModule
import com.anacoimbra.android.filmepedia.features.viewModelModule
import com.anacoimbra.android.filmepedia.repository.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("unused")
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            // use modules
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }
}