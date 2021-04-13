package com.maxnovikov.filmapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FilmDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.film_detail_screen)

        val film: Film? = intent.getParcelableExtra(FILM_DETAIL_DATA_KEY)
        film?.apply {
            findViewById<TextView>(R.id.filmDetailName).text = name
            findViewById<TextView>(R.id.filmDetailYear).text = year.toString()
        }

        findViewById<Button>(R.id.filmDetailClose).setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra(FILM_DETAIL_RATING_KEY, 5))
            finish()
        }
    }

    companion object {
        const val FILM_DETAIL_DATA_KEY = "FILM_DETAIL_DATA_KEY"
        const val FILM_DETAIL_RATING_KEY = "FILM_DETAIL_RATING_KEY"
        const val FILM_DETAIL_RESULT_CODE = 123
    }
}