package com.example.countriesapp.detailedView

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countriesapp.network.CountriesProperty

class DetailedViewModelFactory (
    private val countriesProperty: CountriesProperty,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailedViewModel::class.java)) {
            return DetailedViewModel(countriesProperty,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}