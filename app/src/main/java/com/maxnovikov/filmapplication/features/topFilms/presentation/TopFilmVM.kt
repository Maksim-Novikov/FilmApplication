package com.maxnovikov.filmapplication.features.topFilms.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxnovikov.filmapplication.domain.GetTopFilmsUseCase
import com.maxnovikov.filmapplication.entity.Film
import com.maxnovikov.filmapplication.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class TopFilmVM(
    private val getTopFilmsUseCase: GetTopFilmsUseCase
) : ViewModel() {

    private val _screenState = MutableLiveData<TopFilmScreenState>(TopFilmScreenState.Loading)
    val screenState: LiveData<TopFilmScreenState> = _screenState

    private val _openDetail = SingleLiveEvent<Film>()
    val openDetail: LiveData<Film> = _openDetail

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            _screenState.value = TopFilmScreenState.Error(throwable)
            Log.e("TopFilmVM", throwable.message, throwable)
        }) {
            val films = getTopFilmsUseCase()
            _screenState.value = TopFilmScreenState.Success(films)
        }
    }

    fun onFilmClick(film: Film) {
        _openDetail.value = film
    }
}