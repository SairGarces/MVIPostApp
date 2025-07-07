package com.sairgarces.mvipostsapp.di

import com.sairgarces.mvipostsapp.data.network.ApiService
import com.sairgarces.mvipostsapp.data.repository.PostRepository
import com.sairgarces.mvipostsapp.ui.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    // Singleton para OkHttpClient con logging
    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    // Singleton para Retrofit
    single {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(get()) // Koin inyectará el OkHttpClient definido arriba
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Singleton para ApiService
    single {
        get<Retrofit>().create(ApiService::class.java) // Koin inyectará Retrofit
    }

    // Singleton para PostRepository
    single {
        PostRepository(get()) // Koin inyectará ApiService
    }

    // Definición del ViewModel
    viewModel {
        MainViewModel(get()) // Koin inyectará PostRepository
    }
}