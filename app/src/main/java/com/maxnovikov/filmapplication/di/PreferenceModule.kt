package com.maxnovikov.filmapplication.di

import android.content.Context
import android.content.SharedPreferences
import com.maxnovikov.filmapplication.data.FavoritesDaoImpl
import com.maxnovikov.filmapplication.domain.FavoritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferenceModule {

    @Provides
    @Singleton
    fun providesPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideFavoritesDao(
        favoritesDaoImpl: FavoritesDaoImpl
    ): FavoritesDao = favoritesDaoImpl

}