package com.example.countriesapp.network

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
@Parcelize
data class CountriesProperty(
    val name : Name,
    val flags : ImageUrl,
    val population: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("name"),
        TODO("flags"),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(population)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountriesProperty> {
        override fun createFromParcel(parcel: Parcel): CountriesProperty {
            return CountriesProperty(parcel)
        }

        override fun newArray(size: Int): Array<CountriesProperty?> {
            return arrayOfNulls(size)
        }
    }

}

annotation class Parcelize

data class ImageUrl(
    @Json(name = "png")
    val png : String
)

data class Name(
    val common : String
    )

