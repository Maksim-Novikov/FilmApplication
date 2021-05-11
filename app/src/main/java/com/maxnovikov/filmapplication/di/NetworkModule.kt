package com.maxnovikov.filmapplication.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.maxnovikov.filmapplication.data.FilmApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideFilmApi(): FilmApi = Retrofit.Builder()
        .baseUrl("https://kinopoiskapiunofficial.tech")
        .client(
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request()
                        .newBuilder()
                        .addHeader("X-API-KEY", "541251fa-c296-4fc4-af05-7ae52e3e6401")
                        .build()
                    chain.proceed(request)
                }
                .build()
        )
        .addConverterFactory(
            Json(builderAction = {
                ignoreUnknownKeys = true
                isLenient = true
            }).asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create()
}