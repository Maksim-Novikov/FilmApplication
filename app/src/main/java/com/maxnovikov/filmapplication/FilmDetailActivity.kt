package com.maxnovikov.filmapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView

class FilmDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.film_detail_screen)

        intent.getStringExtra(TopFilmsActivity.FILM_NAME_KEY)?.let { filmName ->
            findViewById<TextView>(R.id.textView).text = filmName
        }
        setResult(
            FILM_DETAIL_RESULT_CODE,
            Intent().putExtra(TopFilmsActivity.FILM_RATING_KEY, 5)
        )
    }

    companion object {
        const val FILM_DETAIL_RESULT_CODE = 10
    }
}