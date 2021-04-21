package com.maxnovikov.filmapplication.features.detail.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.maxnovikov.filmapplication.Film
import com.maxnovikov.filmapplication.R
import com.maxnovikov.filmapplication.common.BaseFragment

class FilmDetailFragment : BaseFragment(R.layout.film_detail_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val film: Film? = arguments?.getParcelable(FILM_DETAIL_DATA_KEY)
        film?.apply {
            view.findViewById<TextView>(R.id.filmDetailName).text = name
            view.findViewById<TextView>(R.id.filmDetailYear).text = year.toString()
        }

        view.findViewById<Button>(R.id.filmDetailClose).setOnClickListener {
            setFragmentResult(FILM_DETAIL_RESULT_KEY, bundleOf(FILM_DETAIL_RATING_KEY to 5))
            parentFragmentManager.popBackStack()
        }
    }

    companion object {

        fun newInstance(film: Film) = FilmDetailFragment().apply {
            arguments = bundleOf(FILM_DETAIL_DATA_KEY to film)
        }


        private const val FILM_DETAIL_DATA_KEY = "FILM_DETAIL_DATA_KEY"
        const val FILM_DETAIL_RATING_KEY = "FILM_DETAIL_RATING_KEY"
        const val FILM_DETAIL_RESULT_KEY = "FILM_DETAIL_RESULT_KEY"
    }
}