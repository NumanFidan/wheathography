package com.simplertutorials.android.wheathograophy.ui.fragments.weatherInfoFragment

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simplertutorials.android.wheathograophy.data.api.ApiRepository
import com.simplertutorials.android.wheathograophy.data.api.ApiService
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.domain.Weather
import com.simplertutorials.android.wheathograophy.subscribe
import com.simplertutorials.android.wheathograophy.ui.fragments.BaseViewModel

class WeatherInfoViewModel(
    private val apiRepository: ApiRepository
) : BaseViewModel() {

    private val requestErrorDialog: MutableLiveData<String> = MutableLiveData()
    private val updateFieldsLiveData: MutableLiveData<City> = MutableLiveData()

    fun getRequestErrorDialog(): LiveData<String> = requestErrorDialog
    fun getUpdateFieldsLiveData(): LiveData<City> = updateFieldsLiveData

    @SuppressLint("CheckResult")
    fun fetchCityWeather(currentCity: City, view: View) {
        //fetch the weather from the API and update the fields
        var weather: Weather? = null
        apiRepository.getWeatherInfo(currentCity)
            .subscribe(
                onNext = { apiWeatherResponse ->
                    weather = Weather(
                        String.format("%.2f", apiWeatherResponse.informationCube!!.temp - 273.15),
                        String.format("%.2f", apiWeatherResponse.informationCube!!.humidity),
                        apiWeatherResponse.weather[0].description
                    );
                },
                onError = { e -> requestErrorDialog.value = e.message },
                onComplete = {
                    val updatedCity = currentCity.copy(weather = weather)
                    updateFieldsLiveData.value = updatedCity
                })
    }

    class Factory(
        private val apiRepository: ApiRepository,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WeatherInfoViewModel(apiRepository) as T
        }

    }
}
