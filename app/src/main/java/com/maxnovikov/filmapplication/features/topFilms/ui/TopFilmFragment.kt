package com.maxnovikov.filmapplication.features.topFilms.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.filmapplication.Film
import com.maxnovikov.filmapplication.R
import com.maxnovikov.filmapplication.common.BaseFragment
import com.maxnovikov.filmapplication.databinding.TopFilmScreenBinding
import com.maxnovikov.filmapplication.features.detail.ui.FilmDetailFragment
import com.maxnovikov.filmapplication.features.search.ui.SearchFragment
import com.maxnovikov.filmapplication.features.topFilms.presentation.TopFilmVM
import com.maxnovikov.filmapplication.utils.navigateTo

class TopFilmFragment : BaseFragment(R.layout.top_film_screen) {

    private val viewBinding by viewBinding(TopFilmScreenBinding::bind)
    private val viewModel by viewModels<TopFilmVM>()
    private var topFilAdapter: TopFilmAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.filmsState.observe(viewLifecycleOwner) { films ->
            topFilAdapter?.submitList(films)
        }
        viewModel.openDetail.observe(viewLifecycleOwner) { film ->
            openDetail(film)
        }
        viewBinding.mainSearch.setOnClickListener {
            parentFragmentManager.navigateTo(SearchFragment())
        }

        with(viewBinding.topFilmsList) {
            topFilAdapter = TopFilmAdapter(onFilmClick = {
                viewModel.onFilmClick(film = it)
            })
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = topFilAdapter
        }
    }

    private fun openDetail(film: Film) {
        parentFragmentManager.navigateTo(FilmDetailFragment.newInstance(film))
    }
}


