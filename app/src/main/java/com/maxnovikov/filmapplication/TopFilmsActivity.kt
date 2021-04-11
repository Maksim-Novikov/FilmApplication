package com.maxnovikov.filmapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.maxnovikov.filmapplication.FilmDetailActivity.Companion.FILM_DETAIL_RESULT_CODE

class TopFilmsActivity : BaseActivity() {

    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.openDetailButton).setOnClickListener {
//            openDetailScreen()
            count++
            findViewById<TextView>(R.id.main_text).text = count.toString()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val rating = data?.getIntExtra(FILM_RATING_KEY, 0)
        Toast.makeText(this, rating.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(FILM_NAME_KEY, count)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState.getInt(FILM_NAME_KEY)
        findViewById<TextView>(R.id.main_text).text = count.toString()
    }

    private fun openDetailScreen() {
        val intent = Intent(this, FilmDetailActivity::class.java)
        intent.putExtra(FILM_NAME_KEY, "Властелин колец")
        startActivityForResult(intent, FILM_DETAIL_RESULT_CODE)

    }

    companion object {
        const val FILM_NAME_KEY = "DATA_KEY"
        const val FILM_RATING_KEY = "FILM_RATING_KEY"
    }
}


