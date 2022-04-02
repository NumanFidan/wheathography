package com.simplertutorials.android.wheathograophy.ui.fragments

import android.annotation.SuppressLint
import android.view.View
import com.simplertutorials.android.wheathograophy.data.api.ApiRepository
import com.simplertutorials.android.wheathograophy.data.api.ApiService
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.domain.Weather
import com.simplertutorials.android.wheathograophy.subscribe

class WeatherInfoPresenter(
    private val view: WeatherInfoFragment,
    private val apiService: ApiService
) {

    private val apiRepository = ApiRepository

    @SuppressLint("CheckResult")
    fun fetchCityWeather(currentCity: City, view: View) {
        //fetch the weather from the API and update the fields
        var weather: Weather? = null
        apiRepository.getWeatherInfo(apiService, currentCity)
            .subscribe(
                onNext = { apiWeatherResponse ->
                    weather = Weather(
                        String.format("%.2f", apiWeatherResponse.informationCube!!.temp - 273.15),
                        String.format("%.2f", apiWeatherResponse.informationCube!!.humidity),
                        apiWeatherResponse.weather[0].description
                    );
                },
                onError = { e -> this.view.showErrorDialog(e) },
                onComplete = {
                    val updatedCity = currentCity.copy(weather = weather)
                    this.view.updateFields(updatedCity, view)
                })
    }

}
