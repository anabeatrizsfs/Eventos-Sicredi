package com.beatriz.eventos

import androidx.multidex.MultiDexApplication
import com.beatriz.eventos.data.di.DataModule.networkModules
import com.beatriz.eventos.di.EventModule.clientOktHttp
import com.beatriz.eventos.di.EventModule.retrofitModule
import com.beatriz.eventos.di.EventModule.userModule
import com.beatriz.eventos.di.EventModule.viewModelModule
import org.koin.core.context.startKoin

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                clientOktHttp,
                retrofitModule,
                networkModules,
                userModule,
                viewModelModule
            )
        }
    }
}
