package com.maxnovikov.filmapplication.di

import android.content.Context
import com.maxnovikov.filmapplication.data.FavoritesDaoImpl
import com.maxnovikov.filmapplication.domain.FavoritesDao

object FavoriteDaoProvider {

    private var dao: FavoritesDao? = null

    fun getDao(context: Context): FavoritesDao {
        return dao ?: FavoritesDaoImpl(
            context.getSharedPreferences("data", Context.MODE_PRIVATE)
        ).also { dao = it }
    }
}