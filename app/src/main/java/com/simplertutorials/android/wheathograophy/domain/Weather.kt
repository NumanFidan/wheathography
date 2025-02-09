package com.simplertutorials.android.wheathograophy.domain


data class Weather(
    val currentTemp: String?,
    val humidity: String?,
    val description: String?,
    val weatherRequestState: RequestState
)  {
    enum class RequestState {
        Loading, Success, Error, Initial
    }
}
