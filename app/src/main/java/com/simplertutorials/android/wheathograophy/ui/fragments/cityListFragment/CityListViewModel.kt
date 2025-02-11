package com.simplertutorials.android.wheathograophy.ui.fragments.cityListFragment

import androidx.lifecycle.*
import com.simplertutorials.android.wheathograophy.data.api.ApiRepository
import com.simplertutorials.android.wheathograophy.data.api.util.onError
import com.simplertutorials.android.wheathograophy.data.api.util.onSuccess
import com.simplertutorials.android.wheathograophy.data.database.StorageRepository
import com.simplertutorials.android.wheathograophy.domain.ApiWeatherResponse
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.domain.Weather
import com.simplertutorials.android.wheathograophy.ui.fragments.BaseViewModel
import com.simplertutorials.android.wheathograophy.ui.fragments.weatherInfoFragment.WeatherInfoFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CityListViewModel(
    private val storageRepository: StorageRepository,
    private val apiRepository: ApiRepository
) : BaseViewModel() {

    private var cityList = listOf<City>()
    private val cityListStateFlow: MutableStateFlow<List<City>> = MutableStateFlow(emptyList())
    private val isRefreshingStateFlow = MutableStateFlow(true)
    private val requestDeleteConfirmationDialogLiveData: MutableLiveData<City> = MutableLiveData()
    private val requestWeatherInfoFragment: MutableLiveData<WeatherInfoFragment> = MutableLiveData()

    fun getCityListLiveData(): StateFlow<List<City>> = cityListStateFlow
    fun getIsRefreshingStateFlow(): StateFlow<Boolean> = isRefreshingStateFlow
    fun getRequestWeatherInfoFragment(): LiveData<WeatherInfoFragment> = requestWeatherInfoFragment

    fun getRequestDeleteConfirmationDialogLiveData(): LiveData<City> =
        requestDeleteConfirmationDialogLiveData

    fun onResume() {
        cityListRefresh()
    }

    fun cityListRefresh() {
        isRefreshingStateFlow.value = true
        cityList = getCurrentCityList().map {
            val weather = it.weather?.copy(weatherRequestState = Weather.RequestState.Loading)
                ?: Weather(null, null, null, Weather.RequestState.Loading)
            it.copy(weather = weather)
        }
        cityListStateFlow.value = cityList
        viewModelScope.launch {
            getCurrentCityList().forEach { city ->
                apiRepository.getWeatherInfo(city)
                    .onSuccess {
                        isRefreshingStateFlow.value = false
                        updateCityWeather(city, it, Weather.RequestState.Success)
                        cityListStateFlow.value = cityList
                    }
                    .onError {
                        isRefreshingStateFlow.value = false
                        updateCityWeather(city, null, Weather.RequestState.Error)
                        cityListStateFlow.value = cityList
                    }
            }
        }
    }

    private fun getCurrentCityList(): List<City> {
        //get the up to date list from database
        return storageRepository.cityList
    }

    private fun deleteCity(city: City) {
        storageRepository.deleteCity(city)
    }

    private fun updateCityWeather(
        city: City,
        apiWeatherResponse: ApiWeatherResponse?,
        requestState: Weather.RequestState
    ) {
        cityList = cityList.toMutableList().apply {
            val weather = Weather(
                apiWeatherResponse?.let { String.format("%.2f", it.informationCube.temp - 273.15) },
                apiWeatherResponse?.let { String.format("%.2f", it.informationCube.humidity) },
                null,
                requestState
            )
            val index = indexOf(city)
            set(index, city.copy(weather = weather))
        }
    }

    fun onCityClicked(city: City) {
        requestWeatherInfoFragment.value = WeatherInfoFragment.newInstance(city)
    }

    fun onCityLongClicked(city: City) {
        requestDeleteConfirmationDialogLiveData.value = city
    }

    fun deleteCityConfirmed(city: City) {
        deleteCity(city)
    }
}
