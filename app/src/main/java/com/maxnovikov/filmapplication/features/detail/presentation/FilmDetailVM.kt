package com.maxnovikov.filmapplication.features.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxnovikov.filmapplication.domain.FavoritesDao
import com.maxnovikov.filmapplication.entity.Film
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FilmDetailVM @AssistedInject constructor(
    private val favoritesDao: FavoritesDao,
    @Assisted private val film: Film
) : ViewModel() {


    @AssistedFactory
    interface Factory {
        fun create(film: Film): FilmDetailVM
    }

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