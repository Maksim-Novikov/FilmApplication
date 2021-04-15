package com.maxnovikov.filmapplication

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.filmapplication.databinding.SearchScreenBinding

class SearchFragment : BaseFragment(R.layout.search_screen) {

    private val viewBinding: SearchScreenBinding by viewBinding(SearchScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            searchSubmit.setOnClickListener {
                // TODO validate data
            }

            searchBack.setOnClickListener {
                parentFragmentManager.popBackStack()
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
                        if (searchRatingToSpinner.selectedItemPosition < position) {
                            searchRatingToSpinner.setSelection(position, true)
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }
        }
    }
}