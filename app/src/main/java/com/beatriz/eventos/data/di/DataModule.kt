package com.beatriz.eventos.data.di

import com.beatriz.eventos.data.network.EventRepository
import com.beatriz.eventos.data.network.api.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

object DataModule {
    val networkModules = module {
        single {
            RemoteDataSource(get())
        }

        single {
            EventRepository(
                get(),
                Dispatchers.IO
            )
        }
    }
}
