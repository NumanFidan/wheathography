package com.simplertutorials.android.wheathograophy.ui.fragments

import android.annotation.SuppressLint
import android.view.View
import com.simplertutorials.android.wheathograophy.data.api.ApiRepository
import com.simplertutorials.android.wheathograophy.data.api.ApiService
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.domain.Weather

class WeatherInfoPresenter(private val view: WeatherInfoFragment,
                           private val apiService: ApiService) {

    private val apiRepository = ApiRepository.getInstance()

    @SuppressLint("CheckResult")
    fun fetchCityWeather(currentCity: City, view: View) {
        //fetch the weather from the API and update the fields
        var weather = Weather()
        apiRepository.getWeatherInfo(apiService,currentCity)
                .subscribe (
                        { n -> weather = Weather(String.format("%.2f",n.informationCube!!.temp - 273.15),
                                String.format("%.2f",n.informationCube!!.humidity),
                                n.weather[0].description); },
                        { e -> this.view.showErrorDialog(e) },
                        { currentCity.weather =  weather
                        this.view.updateFields(currentCity, view)})
    }

}