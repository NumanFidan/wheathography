package com.simplertutorials.android.wheathograophy.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(val name: String, val weather: Weather? = null) : Parcelable
