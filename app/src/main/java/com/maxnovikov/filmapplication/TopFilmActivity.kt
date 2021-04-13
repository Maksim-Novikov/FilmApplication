package com.maxnovikov.filmapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.maxnovikov.filmapplication.FilmDetailActivity.Companion.FILM_DETAIL_DATA_KEY
import com.maxnovikov.filmapplication.FilmDetailActivity.Companion.FILM_DETAIL_RESULT_CODE

class TopFilmActivity : BaseActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.mainAdd)
        button.setOnClickListener {
            count++
            setCountToText()
        }

        findViewById<Button>(R.id.mainShowDetail).setOnClickListener {
            val intent = Intent(this, FilmDetailActivity::class.java)
            val film = Film(name = "Джентельмены", year = 2020)
            intent.putExtra(FILM_DETAIL_DATA_KEY, film)
            startActivityForResult(intent, FILM_DETAIL_RESULT_CODE)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SAVE_COUNT_KEY, count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState.getInt(SAVE_COUNT_KEY)
        setCountToText()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FilmDetailActivity.FILM_DETAIL_RESULT_CODE) {
            val rating = data?.getIntExtra(FilmDetailActivity.FILM_DETAIL_RATING_KEY, 0)
            rating?.let { it -> showRating(it) }
        }
    }

    private fun showRating(rating: Int) {
        Toast.makeText(this, rating.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun setCountToText() {
        val text = findViewById<TextView>(R.id.mainText)
        text.text = count.toString()
    }

    companion object {
        private const val SAVE_COUNT_KEY = "SAVE_COUNT_KEY"
    }
}

