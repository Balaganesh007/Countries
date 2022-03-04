package com.example.countriesapp.network

import android.media.Image
import com.squareup.moshi.Json

data class CountriesProperty(
    val name : Name,
    val flags : ImageUrl
)

data class ImageUrl(
    @Json(name = "png")
    val png : String
)

data class Name(
    val common : String
    )

