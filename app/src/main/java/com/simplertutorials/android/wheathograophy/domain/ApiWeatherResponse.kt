package com.simplertutorials.android.wheathograophy.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiWeatherResponse(
    @SerialName("main") val informationCube: InformationCube,
    @SerialName("weather") val weather: List<WeatherResponse>
)

@Serializable
data class WeatherResponse(
    @SerialName("description") val description: String? = null
)

@Serializable
data class InformationCube(
    @SerialName("temp") val temp: Float,
    @SerialName("humidity") val humidity: Float
)
