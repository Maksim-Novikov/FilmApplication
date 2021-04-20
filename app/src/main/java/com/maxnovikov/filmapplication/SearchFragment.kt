package com.maxnovikov.filmapplication

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.filmapplication.databinding.SearchScreenBinding

class SearchFragment : BaseFragment(R.layout.search_screen) {

    private val viewBinding: SearchScreenBinding by viewBinding(SearchScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            searchBack.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            searchRatingToSpinner.setSelection(9)
        }
    }
}