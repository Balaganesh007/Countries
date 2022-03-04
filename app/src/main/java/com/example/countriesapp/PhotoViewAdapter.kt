package com.example.countriesapp


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.countriesapp.network.CountriesProperty
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.databinding.GridViewItemBinding

class PhotoViewAdapter : ListAdapter<CountriesProperty, PhotoViewAdapter.CountriesPropertyViewHolder>(DiffCallback) {
    class CountriesPropertyViewHolder(private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(countriesProperty: CountriesProperty) {
            binding.property = countriesProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CountriesProperty>(){
        override fun areItemsTheSame(
            oldItem: CountriesProperty,
            newItem: CountriesProperty
        ): Boolean {
            return oldItem  == newItem
        }

        override fun areContentsTheSame(
            oldItem: CountriesProperty,
            newItem: CountriesProperty
        ): Boolean {
            return oldItem.flags == newItem.flags
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoViewAdapter.CountriesPropertyViewHolder {
        return CountriesPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: PhotoViewAdapter.CountriesPropertyViewHolder,
        position: Int
    ) {
        val countriesProperty = getItem(position)
        holder.bind(countriesProperty)
    }

}