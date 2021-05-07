package com.maxnovikov.filmapplication.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopFilmResponse(
    @SerialName("films")
    val films: List<FilmNw>?,
    @SerialName("pagesCount")
    val pagesCount: Int?
)