package com.maxnovikov.filmapplication.features.search.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.filmapplication.R
import com.maxnovikov.filmapplication.common.BaseFragment
import com.maxnovikov.filmapplication.databinding.SearchScreenBinding
import com.maxnovikov.filmapplication.features.search.presentation.SearchErrorType
import com.maxnovikov.filmapplication.features.search.presentation.SearchType
import com.maxnovikov.filmapplication.features.search.presentation.SearchVM

class SearchFragment : BaseFragment(R.layout.search_screen) {

    private var toast: Toast? = null
    private val viewModel: SearchVM by viewModels()
    private val viewBinding: SearchScreenBinding by viewBinding(SearchScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loading.observe(viewLifecycleOwner) { showLoading ->
            viewBinding.searchProgress.isVisible = showLoading
            viewBinding.searchProgressView.isVisible = showLoading
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorType ->
            showMessage(getString(errorType.getString()))
        }

        viewModel.successMessage.observe(viewLifecycleOwner) {
            showMessage(getString(R.string.saerch_success))
        }

        viewBinding.apply {
            searchBack.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            searchSubmit.setOnClickListener {
                viewModel.onSubmit(
                    searchType = when (searchTypeGroup.checkedRadioButtonId) {
                        R.id.searchTypeFilms -> SearchType.FILMS
                        R.id.searchTypeTvShow -> SearchType.TV_SHOW
                        else -> SearchType.ALL
                    },
                    ratingFrom = (searchRatingFromSpinner.selectedItem as String).toInt(),
                    ratingTo = (searchRatingToSpinner.selectedItem as String).toInt(),
                    yearFromString = searchYearFromEdit.text.toString(),
                    yearToString = searchYearToEdit.text.toString()
                )
            }
            searchRatingToSpinner.setSelection(9)
            searchRatingFromSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (position > searchRatingToSpinner.selectedItemPosition) {
                            searchRatingToSpinner.setSelection(position)
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }

            searchRatingToSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (position < searchRatingFromSpinner.selectedItemPosition) {
                            searchRatingFromSpinner.setSelection(position)
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
        }
    }

    private fun showMessage(message: String) {
        context?.let {
            toast?.cancel()
            toast = Toast.makeText(it, message, Toast.LENGTH_LONG).apply { show() }
        }
    }

    private fun SearchErrorType.getString(): Int =
        when (this) {
            SearchErrorType.YEAR_FROM_MORE_THAN_TO -> R.string.year_from_more_than_to
        }
}