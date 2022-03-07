package com.example.countriesapp.displayall

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.countriesapp.PhotoViewAdapter
import com.example.countriesapp.R
import com.example.countriesapp.databinding.DisplayFragmentBinding

class DisplayFragment : Fragment() {

    private lateinit var binding : DisplayFragmentBinding
    private lateinit var viewModel : DisplayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.display_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(DisplayViewModel::class.java)
        binding.displayViewModel = viewModel
        binding.lifecycleOwner = this

        //binding.photosGrid.adapter = PhotoViewAdapter()
        binding.photosGrid.adapter = PhotoViewAdapter(PhotoViewAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(DisplayFragmentDirections.actionDisplayFragmentToDetailedView(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        return binding.root
    }
}