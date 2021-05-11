package com.maxnovikov.filmapplication.data

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.maxnovikov.filmapplication.domain.FavoritesDao
import com.maxnovikov.filmapplication.entity.Film
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FavoritesDaoImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : FavoritesDao {

    private val filmsData = MutableLiveData(films)

    private var films: List<Film>
        get() = sharedPreferences.getString(FAVORITE_DAO_KEY, null)?.let {
            Json.decodeFromString(it)
        } ?: emptyList()
        set(value) {
            sharedPreferences.edit()
                .putString(FAVORITE_DAO_KEY, Json.encodeToString(value))
                .apply()
            filmsData.value = value
        }

    override fun add(film: Film) {
        films = films + film
    }

    override fun delete(film: Film) {
        films = films - film
    }

    override fun getAll(): LiveData<List<Film>> = filmsData

    override fun isInFavorites(film: Film): Boolean = films.contains(film)


    companion object {
        private const val FAVORITE_DAO_KEY = "FAVORITE_DAO_KEY"
    }
}