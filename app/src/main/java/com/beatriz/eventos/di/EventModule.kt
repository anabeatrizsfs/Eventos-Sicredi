package com.beatriz.eventos.di

import com.beatriz.eventos.data.constants.EventConstants.BASE_URL
import com.beatriz.eventos.data.network.EventAPI
import org.koin.dsl.module
import retrofit2.Retrofit

object EventModule {
    val networkModule = module {
        single {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EventAPI::class.java)
        }
    }
}