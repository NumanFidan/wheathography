package com.simplertutorials.android.wheathograophy.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(val currentTemp: String?, val humidity: String?, val description: String?) :Parcelable
