package com.maxnovikov.filmapplication.features.topFilms.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import com.maxnovikov.filmapplication.Film
import com.maxnovikov.filmapplication.R
import com.maxnovikov.filmapplication.common.BaseFragment
import com.maxnovikov.filmapplication.features.detail.ui.FilmDetailFragment
import com.maxnovikov.filmapplication.features.detail.ui.FilmDetailFragment.Companion.FILM_DETAIL_RATING_KEY
import com.maxnovikov.filmapplication.features.detail.ui.FilmDetailFragment.Companion.FILM_DETAIL_RESULT_KEY
import com.maxnovikov.filmapplication.features.search.ui.SearchFragment

class TopFilmFragment : BaseFragment(R.layout.top_film_screen) {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(FILM_DETAIL_RESULT_KEY) { requestKey, bundle ->
            if (requestKey == FILM_DETAIL_RESULT_KEY) {
                val rating = bundle.getInt(FILM_DETAIL_RATING_KEY, 0)
                showRating(rating)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.mainAdd)
        button.setOnClickListener {
            count++
            setCountToText()
        }

        view.findViewById<Button>(R.id.mainShowDetail).setOnClickListener {
            val film = Film(name = "Джентельмены", year = 2020)
            val fragment = FilmDetailFragment.newInstance(film)
            parentFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_from_right,
                    R.anim.slide_to_left,
                    R.anim.slide_from_left,
                    R.anim.slide_to_right
                )
                .replace(R.id.mainContainer, fragment, fragment::class.java.canonicalName)
                .addToBackStack(fragment::class.java.canonicalName)
                .commitAllowingStateLoss()
        }

        view.findViewById<Button>(R.id.mainSearch).setOnClickListener {
            val fragment = SearchFragment()
            parentFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_from_right,
                    R.anim.slide_to_left,
                    R.anim.slide_from_left,
                    R.anim.slide_to_right
                )
                .replace(R.id.mainContainer, fragment, fragment::class.java.canonicalName)
                .addToBackStack(fragment::class.java.canonicalName)
                .commitAllowingStateLoss()

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SAVE_COUNT_KEY, count)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        count = savedInstanceState?.getInt(SAVE_COUNT_KEY) ?: 0
        setCountToText()
    }

    private fun showRating(rating: Int) {
        context?.let {
            Toast.makeText(it, rating.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setCountToText() {

        val text = view?.findViewById<TextView>(R.id.mainText)
        text?.text = count.toString()
    }

    companion object {
        private const val SAVE_COUNT_KEY = "SAVE_COUNT_KEY"
    }
}


