package com.example.countriesapp.detailedView

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countriesapp.network.CountriesProperty

class DetailedViewModel(countriesProperty: CountriesProperty, application: Application) : ViewModel() {
    private var _selectedProperty = MutableLiveData<CountriesProperty>()
    val selectedProperty : LiveData<CountriesProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = countriesProperty
    }
}