package com.beatriz.eventos.di

import com.beatriz.eventos.BuildConfig
import com.beatriz.eventos.data.constants.EventConstants.BASE_URL
import com.beatriz.eventos.data.model.User
import com.beatriz.eventos.data.network.api.EventAPI
import com.beatriz.eventos.ui.event.EventDetailViewModel
import com.beatriz.eventos.ui.main.MainViewModel
import com.beatriz.eventos.ui.welcome.WelcomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object EventModule {

    val clientOktHttp = module {
        single {
            OkHttpClient.Builder()
                .addInterceptor(logInterceptor()).build()
        }
    }

    val retrofitModule = module {
        single {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
                .create(EventAPI::class.java)
        }
    }

    private fun logInterceptor(): HttpLoggingInterceptor {
        val logInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return logInterceptor
    }

    val viewModelModule = module {
        viewModel {
            MainViewModel(get())
        }
        viewModel {
            EventDetailViewModel(get(), get())
        }
        viewModel {
            WelcomeViewModel(get())
        }
    }

    val userModule = module {
        single {
            User()
        }
    }
}
