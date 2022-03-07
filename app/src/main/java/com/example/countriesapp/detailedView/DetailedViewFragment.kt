package com.example.countriesapp.detailedView

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.countriesapp.R
import com.example.countriesapp.databinding.DetailedViewFragmentBinding



class DetailedViewFragment : Fragment() {

    private lateinit var viewModel: DetailedViewModel
    private lateinit var binding: DetailedViewFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.detailed_view_fragment,
            container,
            false
        )
//        viewModel = ViewModelProvider(this).get(DetailedViewModel::class.java)
//        binding.detailedViewModel = viewModel
        binding.lifecycleOwner = this

        val countriesProperty = arguments?.let { DetailedViewFragmentArgs.fromBundle(it).selectedProperty }
        Log.v("bala","$countriesProperty")
        val application = requireNotNull(this.activity).application

        val viewModelFactory = countriesProperty?.let { DetailedViewModelFactory(it,application) }
        viewModel = viewModelFactory?.let {
            ViewModelProvider(
                this, it
            ).get(DetailedViewModel::class.java)
        }!!

        binding.countryNameTextView.text = viewModel.selectedProperty.value?.name?.common
        binding.populationTextView.text = viewModel.selectedProperty.value?.population


        return binding.root
    }

}