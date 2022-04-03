package com.simplertutorials.android.wheathograophy.domain

import com.google.gson.annotations.SerializedName

data class ApiWeatherResponse(
    @SerializedName("main") val informationCube: InformationCube,
    @SerializedName("weather") val weather: List<WeatherResponse>
)

data class WeatherResponse(
    @SerializedName("description") val description: String? = null
)

data class InformationCube(
    @SerializedName("temp") val temp: Float,
    @SerializedName("humidity") val humidity: Float
)
