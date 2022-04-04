package com.simplertutorials.android.wheathograophy.ui.fragments.cityListFragment

import androidx.lifecycle.*
import com.simplertutorials.android.wheathograophy.data.api.ApiRepository
import com.simplertutorials.android.wheathograophy.data.database.StorageRepository
import com.simplertutorials.android.wheathograophy.domain.ApiWeatherResponse
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.domain.Weather
import com.simplertutorials.android.wheathograophy.ui.fragments.BaseViewModel
import com.simplertutorials.android.wheathograophy.ui.fragments.weatherInfoFragment.WeatherInfoFragment
import com.simplertutorials.android.wheathograophy.subscribe

class CityListViewModel(
    private val storageRepository: StorageRepository,
    private val apiRepository: ApiRepository
) : BaseViewModel() {

    private var cityList = listOf<City>()
    private val cityListLiveData: MutableLiveData<List<City>> = MutableLiveData()
    private val requestRefreshLiveData = MutableLiveData(true)
    private val requestDeleteConfirmationDialogLiveData: MutableLiveData<City> = MutableLiveData()
    private val requestWeatherInfoFragment: MutableLiveData<WeatherInfoFragment> = MutableLiveData()

    fun getCityListLiveData(): LiveData<List<City>> = cityListLiveData
    fun getRequestRefreshLiveData(): LiveData<Boolean> = requestRefreshLiveData
    fun getRequestWeatherInfoFragment(): LiveData<WeatherInfoFragment> = requestWeatherInfoFragment

    fun getRequestDeleteConfirmationDialogLiveData(): LiveData<City> =
        requestDeleteConfirmationDialogLiveData

    fun onResume() {
        cityListRefresh()
    }

    fun cityListRefresh() {
        requestRefreshLiveData.value = true
        cityList = getCurrentCityList().map {
            val weather = it.weather?.copy(weatherRequestState = Weather.RequestState.Loading)
                ?: Weather(null, null, null, Weather.RequestState.Loading)
            it.copy(weather = weather)
        }
        cityListLiveData.value = cityList
        getCurrentCityList().forEach { city ->
            apiRepository.getWeatherInfo(city)
                .subscribe(
                    onNext = { apiWeatherResponse ->
                        requestRefreshLiveData.postValue(false)
                        updateCityWeather(city, apiWeatherResponse, Weather.RequestState.Success)
                        cityListLiveData.postValue(cityList)
                    },
                    onError = { e ->
                        requestRefreshLiveData.postValue(false)
                        updateCityWeather(city, null, Weather.RequestState.Error)
                        cityListLiveData.postValue(cityList)
                    })
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

    class Factory(
        private val storageRepository: StorageRepository,
        private val apiRepository: ApiRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CityListViewModel(storageRepository, apiRepository) as T
        }
    }
}
