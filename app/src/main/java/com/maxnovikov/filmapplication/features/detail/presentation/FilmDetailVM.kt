package com.maxnovikov.filmapplication.features.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxnovikov.filmapplication.domain.FavoritesDao
import com.maxnovikov.filmapplication.entity.Film

class FilmDetailVM(
    private val favoritesDao: FavoritesDao,
    private val film: Film
) : ViewModel() {

    private val _favoritesState = MutableLiveData(favoritesDao.isInFavorites(film))
    val favoritesState: LiveData<Boolean> = _favoritesState

    fun onFavoritesClick() {
        val isInFavorites = !(_favoritesState.value ?: false)
        _favoritesState.value = isInFavorites
        if (isInFavorites) {
            favoritesDao.add(film)
        } else {
            favoritesDao.delete(film)
        }
    }

}