package com.maxnovikov.filmapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FilmDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.film_detail_screen)

        intent.getParcelableExtra<Film>(FILM_DATA_KEY)?.let { film ->
            findViewById<TextView>(R.id.filmName).text = film.name
            findViewById<TextView>(R.id.filmYear).text = film.year.toString()
        }
        findViewById<Button>(R.id.filmRate).setOnClickListener {
            setResult(
                FILM_DETAIL_RESULT_CODE,
                Intent().putExtra(TopFilmsActivity.FILM_RATING_KEY, 5)
            )
            finish()
        }
    }

    companion object {
        const val FILM_DATA_KEY = "DATA_KEY"
        const val FILM_DETAIL_RESULT_CODE = 10
    }
}