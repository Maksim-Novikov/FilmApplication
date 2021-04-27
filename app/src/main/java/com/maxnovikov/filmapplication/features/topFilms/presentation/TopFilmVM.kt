package com.maxnovikov.filmapplication.features.topFilms.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxnovikov.filmapplication.Film
import com.maxnovikov.filmapplication.utils.SingleLiveEvent

class TopFilmVM : ViewModel() {

    private var films = listOf(
        Film("Джентельмены", 2020),
        Film("Крик", 2002),
        Film("Молчание ягнят", 2005),
        Film("Властелин Колец", 2001),
        Film("Генв человеческий", 2021),
        Film("Майор Гром: Чумной Доктор", 2021),
        Film("Чернобыль", 2020),
        Film("Зеленая миля", 1999),
        Film("Интерстеллар", 2014),
        Film("Иван Васильевич меняет профессию", 1973),
        Film("Тайна Коко", 2017),
        Film("Карты, деньги, два ствола", 1998)
    )

    private val _filmsState = MutableLiveData(films)
    val filmsState: LiveData<List<Film>> = _filmsState

    private val _openDetail = SingleLiveEvent<Film>()
    val openDetail: LiveData<Film> = _openDetail

    fun onFilmClick(film: Film) {
        _openDetail.value = film
    }

}