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

class DisplayViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private var _properties = MutableLiveData<List<CountriesProperty>>()
    val properties: LiveData<List<CountriesProperty>>
        get() = _properties

    init {
        getCountriesProperties()
    }

    private fun getCountriesProperties() {
        coroutineScope.launch {

            var getPropertiesDeferred = ObjectCountriesApi.retrofitService.getPropertiesAsync()
            try {
                var listResult = getPropertiesDeferred.await()
                _status.value = "Success: ${listResult.size} Mars properties retrieved"
                if(listResult.size > 0) _properties.value = listResult
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}