package com.maxnovikov.filmapplication.domain

import com.maxnovikov.filmapplication.data.FilmApi
import com.maxnovikov.filmapplication.entity.Film

class GetTopFilmsUseCase(
    private val filmApi: FilmApi
) {

    suspend operator fun invoke(): List<Film> {
        return filmApi.getTopFilms(
            type = "TOP_250_BEST_FILMS",
            page = 1
        ).films?.mapNotNull {
            Film(
                name = it.nameRu ?: return@mapNotNull null,
                year = it.year?.toIntOrNull() ?: return@mapNotNull null
            )
        } ?: emptyList()
    }

}