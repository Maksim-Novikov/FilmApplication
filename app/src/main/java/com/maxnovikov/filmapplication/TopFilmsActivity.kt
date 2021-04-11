package com.maxnovikov.filmapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.maxnovikov.filmapplication.FilmDetailActivity.Companion.FILM_DATA_KEY
import com.maxnovikov.filmapplication.FilmDetailActivity.Companion.FILM_DETAIL_RESULT_CODE

class TopFilmsActivity : BaseActivity() {

    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.openDetailButton).setOnClickListener {
            openDetailScreen()
//            count++
//            findViewById<TextView>(R.id.main_text).text = count.toString()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.getIntExtra(FILM_RATING_KEY, 0)?.let { rating ->
            showRating(rating)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(FILM_DATA_KEY, count)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState.getInt(FILM_DATA_KEY)
        findViewById<TextView>(R.id.main_text).text = count.toString()
    }

    private fun openDetailScreen() {
        val intent = Intent(this, FilmDetailActivity::class.java)
        intent.putExtra(
            FILM_DATA_KEY,
            Film(name = "Властелин колец", year = 2002)
        )
        startActivityForResult(intent, FILM_DETAIL_RESULT_CODE)
    }

    private fun showRating(rating: Int) {
        Toast.makeText(this, rating.toString(), Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val FILM_RATING_KEY = "FILM_RATING_KEY"
    }
}


