package com.maxnovikov.filmapplication.data

import com.maxnovikov.filmapplication.data.entity.TopFilmResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmApi {

    @GET("/api/v2.2/films/top")
    suspend fun getTopFilms(
        @Query("type") type: String,
        @Query("page") page: Int
    ): TopFilmResponse

}