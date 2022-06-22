package com.beatriz.eventos.di

import com.beatriz.eventos.data.constants.EventConstants.BASE_URL
import com.beatriz.eventos.data.network.api.EventAPI
import com.beatriz.eventos.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object EventModule {
    val retrofitModule = module {
        single {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EventAPI::class.java)
        }
    }

    val viewModelModule = module {
        viewModel {
            MainViewModel(get())
        }
    }
}
