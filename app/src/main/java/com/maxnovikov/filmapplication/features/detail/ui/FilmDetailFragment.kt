package com.maxnovikov.filmapplication.features.detail.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.filmapplication.R
import com.maxnovikov.filmapplication.common.BaseFragment
import com.maxnovikov.filmapplication.databinding.FilmDetailScreenBinding
import com.maxnovikov.filmapplication.di.FavoriteDaoProvider
import com.maxnovikov.filmapplication.entity.Film
import com.maxnovikov.filmapplication.features.detail.presentation.FilmDetailVM

class FilmDetailFragment : BaseFragment(R.layout.film_detail_screen) {

    private val viewBinding by viewBinding(FilmDetailScreenBinding::bind)
    private val viewModel by viewModels<FilmDetailVM> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return FilmDetailVM(
                    favoritesDao = FavoriteDaoProvider.getDao(context!!),
                    film = arguments?.getParcelable(FILM_DETAIL_DATA_KEY)!!

                ) as T
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val film: Film? = arguments?.getParcelable(FILM_DETAIL_DATA_KEY)
        film?.apply {
            viewBinding.filmDetailName.text = name
            viewBinding.filmDetailYear.text = year.toString()
        }

        viewBinding.filmDetailClose.setOnClickListener {
            setFragmentResult(FILM_DETAIL_RESULT_KEY, bundleOf(FILM_DETAIL_RATING_KEY to 5))
            parentFragmentManager.popBackStack()
        }
        viewBinding.filmDetailFavorite.setOnClickListener {
            viewModel.onFavoritesClick()
        }

        viewModel.favoritesState.observe(viewLifecycleOwner) { isInFavorite ->
            viewBinding.filmDetailFavorite.setImageResource(
                if (isInFavorite) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
            )
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