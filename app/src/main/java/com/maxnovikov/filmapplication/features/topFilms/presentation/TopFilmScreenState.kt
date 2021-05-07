package com.maxnovikov.filmapplication.features.topFilms.presentation

import com.maxnovikov.filmapplication.entity.Film

sealed class TopFilmScreenState {
    object Loading : TopFilmScreenState()
    class Success(val film: List<Film>) : TopFilmScreenState()
    class Error(val throwable: Throwable) : TopFilmScreenState()
}