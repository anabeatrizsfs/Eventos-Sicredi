package com.beatriz.eventos.data.di

import com.beatriz.eventos.data.network.api.ApiHelper
import com.beatriz.eventos.data.network.EventRepository
import org.koin.dsl.module

object DataModule {
    val networkModules = module {
        single {
            ApiHelper(get())
        }

        single {
            EventRepository(get())
        }
    }
}