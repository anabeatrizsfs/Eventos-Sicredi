package com.beatriz.eventos

import android.app.Application
import com.beatriz.eventos.di.EventModule.networkModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(networkModule)
        }
    }
}