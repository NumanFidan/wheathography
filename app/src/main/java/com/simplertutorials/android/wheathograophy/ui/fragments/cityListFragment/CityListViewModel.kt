package com.simplertutorials.android.wheathograophy.ui.fragments.cityListFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simplertutorials.android.wheathograophy.data.database.DatabaseRepository
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.ui.fragments.BaseViewModel

class CityListViewModel(private val databaseRepository: DatabaseRepository) : BaseViewModel() {

    fun getCurrentCityList(cityList: ArrayList<City>) {
        //get the up to date list from database
        cityList.clear()
        cityList.addAll(databaseRepository.cityList)
    }

    fun deleteCity(city: City) {
        databaseRepository.deleteCity(city)
    }

    class Factory(private val databaseRepository: DatabaseRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CityListViewModel(databaseRepository) as T
        }
    }
}
