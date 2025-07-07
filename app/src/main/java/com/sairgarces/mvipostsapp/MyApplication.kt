package com.sairgarces.mvipostsapp


import android.app.Application
import com.sairgarces.mvipostsapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Logger de Koin (útil para debug)
            androidLogger()
            // Proporcionar el contexto de Android a Koin
            androidContext(this@MyApplication)
            // Cargar nuestros módulos
            modules(appModule)
        }
    }
}