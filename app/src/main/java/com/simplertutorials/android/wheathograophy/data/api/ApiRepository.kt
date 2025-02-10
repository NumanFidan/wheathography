package com.simplertutorials.android.wheathograophy.data.api

import com.simplertutorials.android.wheathograophy.BuildConfig
import com.simplertutorials.android.wheathograophy.data.api.util.NetworkError
import com.simplertutorials.android.wheathograophy.data.api.util.Result
import com.simplertutorials.android.wheathograophy.domain.ApiWeatherResponse
import com.simplertutorials.android.wheathograophy.domain.City

class ApiRepository(
    private val apiService: ApiService
) {

    suspend fun getWeatherInfo(city: City): Result<ApiWeatherResponse, NetworkError> {
        return apiService.getWeather(
            city.name,
            BuildConfig.API_KEY
        )
    }
}
