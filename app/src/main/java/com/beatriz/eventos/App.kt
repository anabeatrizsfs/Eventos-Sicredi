package com.beatriz.eventos

import android.app.Application
import com.beatriz.eventos.data.di.DataModule.networkModules
import com.beatriz.eventos.di.EventModule.retrofitModule
import com.beatriz.eventos.di.EventModule.viewModelModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                retrofitModule,
                networkModules,
                viewModelModule
            )
        }
    }
}
