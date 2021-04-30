package com.maxnovikov.filmapplication.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Film(
    val name: String,
    val year: Int
) : Parcelable