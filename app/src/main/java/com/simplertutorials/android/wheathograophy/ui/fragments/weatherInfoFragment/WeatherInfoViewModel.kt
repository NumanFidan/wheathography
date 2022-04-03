package com.simplertutorials.android.wheathograophy.ui.fragments.weatherInfoFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simplertutorials.android.wheathograophy.data.api.ApiService
import com.simplertutorials.android.wheathograophy.data.database.DatabaseRepository
import com.simplertutorials.android.wheathograophy.ui.fragments.BaseViewModel

class WeatherInfoViewModel(
    private val apiRepository: DatabaseRepository,
    private val apiService: ApiService
) : BaseViewModel() {

    class Factory(
        private val apiRepository: DatabaseRepository,
        private val apiService: ApiService
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WeatherInfoViewModel(apiRepository, apiService) as T
        }

    }
}
