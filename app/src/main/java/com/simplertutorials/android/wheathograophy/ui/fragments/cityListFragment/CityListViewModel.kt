package com.simplertutorials.android.wheathograophy.ui.fragments.cityListFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simplertutorials.android.wheathograophy.data.database.StorageRepository
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.ui.fragments.BaseViewModel

class CityListViewModel(private val storageRepository: StorageRepository) : BaseViewModel() {

    fun getCurrentCityList(cityList: ArrayList<City>) {
        //get the up to date list from database
        cityList.clear()
        cityList.addAll(storageRepository.cityList)
    }

    fun deleteCity(city: City) {
        storageRepository.deleteCity(city)
    }

    class Factory(private val storageRepository: StorageRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CityListViewModel(storageRepository) as T
        }
    }
}
