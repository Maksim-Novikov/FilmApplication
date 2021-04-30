package com.maxnovikov.filmapplication.domain

import androidx.lifecycle.LiveData
import com.maxnovikov.filmapplication.entity.Film

interface FavoritesDao {

    /**
     * Добавление в избранное
     * */
    fun add(film: Film)

    /**
     * Удаление из избранного
     * */
    fun delete(film: Film)

    /**
     * Получение списка избранного
     * Может быть пустым
     * */
    fun getAll(): LiveData<List<Film>>

    /**
     * @return true если фильм находится в избранном
     * */
    fun isInFavorites(film: Film): Boolean
}

