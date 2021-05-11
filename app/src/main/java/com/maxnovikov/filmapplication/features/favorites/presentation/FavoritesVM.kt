package com.maxnovikov.filmapplication.features.favorites.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.maxnovikov.filmapplication.domain.FavoritesDao
import com.maxnovikov.filmapplication.entity.Film
import com.maxnovikov.filmapplication.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesVM @Inject constructor(
    private val favoritesDao: FavoritesDao
) : ViewModel() {


    val favoritesState = favoritesDao.getAll()

    private val _openDetail = SingleLiveEvent<Film>()
    val openDetail: LiveData<Film> = _openDetail

    fun onFilmClick(film: Film) {
        _openDetail.value = film
    }
}