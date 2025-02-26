package com.simplertutorials.android.wheathograophy.ui.screens.weatherInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.simplertutorials.android.wheathograophy.data.api.ApiRepository
import com.simplertutorials.android.wheathograophy.data.api.util.onError
import com.simplertutorials.android.wheathograophy.data.api.util.onSuccess
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.domain.Weather
import com.simplertutorials.android.wheathograophy.ui.screens.BaseViewModel
import com.simplertutorials.android.wheathograophy.ui.screens.cityList.CityListFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherInfoViewModel(
    private val apiRepository: ApiRepository
) : BaseViewModel() {

    private val requestErrorDialog: MutableLiveData<String> = MutableLiveData()
    private val requestCityListFragment: MutableLiveData<CityListFragment> = MutableLiveData()
    private val updateFieldsLiveData: MutableStateFlow<City> = MutableStateFlow(City("Default", null))

    fun getRequestErrorDialog(): LiveData<String> = requestErrorDialog
    fun getRequestCityListFragment(): LiveData<CityListFragment> = requestCityListFragment
    fun getUpdateFieldsLiveData(): StateFlow<City> = updateFieldsLiveData

    fun passArguments(currentCity: City) {
        fetchCityWeather(currentCity)
    }

    fun errorDialogClosed() {
        requestCityListFragment.postValue(CityListFragment())
    }

    private fun fetchCityWeather(currentCity: City) {
        //fetch the weather from the API and update the fields
        var weather: Weather? = null
        viewModelScope.launch {
            apiRepository.getWeatherInfo(currentCity)
                .onSuccess { apiWeatherResponse ->
                    weather = Weather(
                        currentTemp = String.format(
                            "%.2f",
                            apiWeatherResponse.informationCube.temp - 273.15
                        ),
                        humidity = String.format(
                            "%.2f",
                            apiWeatherResponse.informationCube.humidity
                        ),
                        description = apiWeatherResponse.weather[0].description,
                        weatherRequestState = Weather.RequestState.Success
                    )
                    val updatedCity = currentCity.copy(weather = weather)
                    updateFieldsLiveData.value = updatedCity
                }
                .onError {
                    requestErrorDialog.value = it.name
                }
                .also {
                    val updatedCity = currentCity.copy(weather = weather)
                    updateFieldsLiveData.value = updatedCity
                }
        }
    }
}
