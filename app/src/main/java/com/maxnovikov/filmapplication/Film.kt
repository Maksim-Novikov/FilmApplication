package com.maxnovikov.filmapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Film(
    val name: String,
    val year: Int
) : Parcelable