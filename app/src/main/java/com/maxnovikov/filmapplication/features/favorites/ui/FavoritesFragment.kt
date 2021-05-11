package com.maxnovikov.filmapplication.features.favorites.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.filmapplication.R
import com.maxnovikov.filmapplication.common.BaseFragment
import com.maxnovikov.filmapplication.databinding.FavoritesScreenBinding
import com.maxnovikov.filmapplication.features.detail.ui.FilmDetailFragment
import com.maxnovikov.filmapplication.features.favorites.presentation.FavoritesVM
import com.maxnovikov.filmapplication.features.topFilms.ui.TopFilmAdapter
import com.maxnovikov.filmapplication.utils.navigateTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseFragment(R.layout.favorites_screen) {

    private val viewBinding by viewBinding(FavoritesScreenBinding::bind)
    private val viewModel by viewModels<FavoritesVM>()

    private var favoritesAdapter: TopFilmAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding.favoritesList) {
            favoritesAdapter = TopFilmAdapter(viewModel::onFilmClick)
            layoutManager = LinearLayoutManager(context)
            adapter = favoritesAdapter
        }

        viewModel.favoritesState.observe(viewLifecycleOwner) { films ->
            favoritesAdapter?.submitList(films)
        }

        viewModel.openDetail.observe(viewLifecycleOwner) { film ->
            parentFragmentManager.navigateTo(FilmDetailFragment.newInstance(film))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        favoritesAdapter = null
    }
}