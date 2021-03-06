package com.example.countriesapp.displayall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countriesapp.network.CountriesProperty
import com.example.countriesapp.network.ObjectCountriesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class CountryApiStatus {LOADING , ERROR , DONE}
class DisplayViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    private var _status = MutableLiveData<CountryApiStatus>()
    val status: LiveData<CountryApiStatus>
        get() = _status

    private var _properties = MutableLiveData<List<CountriesProperty>>()
    val properties: LiveData<List<CountriesProperty>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<CountriesProperty?>()
    val navigateToSelectedProperty: MutableLiveData<CountriesProperty?>
        get() = _navigateToSelectedProperty

    init {
        getCountriesProperties()
    }

    private fun getCountriesProperties() {
        coroutineScope.launch {

            val getPropertiesDeferred = ObjectCountriesApi.retrofitService.getPropertiesAsync()
            try {
                _status.value = CountryApiStatus.LOADING
                val listResult = getPropertiesDeferred.await()

                _status.value = CountryApiStatus.DONE
                if(listResult.isNotEmpty()) _properties.value = listResult
            } catch (e: Exception) {
                _status.value = CountryApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }
    fun displayPropertyDetails(countriesProperty: CountriesProperty) {
        _navigateToSelectedProperty.value = countriesProperty
    }
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}