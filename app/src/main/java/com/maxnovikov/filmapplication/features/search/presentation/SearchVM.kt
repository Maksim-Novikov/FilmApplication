package com.maxnovikov.filmapplication.features.search.presentation

import android.os.Handler
import androidx.core.os.postDelayed
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxnovikov.filmapplication.utils.SingleLiveEvent

class SearchVM : ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _errorMessage = SingleLiveEvent<SearchErrorType>()
    val errorMessage: LiveData<SearchErrorType> = _errorMessage

    private val _successMessage = SingleLiveEvent<Unit>()
    val successMessage = _successMessage

    private var isRequestInProgress: Boolean = false

    fun onSubmit(
        searchType: SearchType,
        ratingFrom: Int,
        ratingTo: Int,
        yearFromString: String,
        yearToString: String
    ) {
        if (isRequestInProgress) return

        val yearFrom: Int? = yearFromString.toIntOrNull()
        val yearTo: Int? = yearToString.toIntOrNull()

        if (yearFrom != null && yearTo != null && yearFrom > yearTo) {

            _errorMessage.value = SearchErrorType.YEAR_FROM_MORE_THAN_TO
            return
        }

        _loading.value = true
        isRequestInProgress = true
        Handler().postDelayed(2000) {
            _loading.value = false
            successMessage.call()
            isRequestInProgress = false
        }
    }
}

