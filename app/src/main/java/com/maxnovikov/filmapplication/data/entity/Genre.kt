package com.maxnovikov.filmapplication.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    @SerialName("genre")
    val genre: String?
)