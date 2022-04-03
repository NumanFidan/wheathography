package com.simplertutorials.android.wheathograophy.ui.fragments.cityListFragment

import com.simplertutorials.android.wheathograophy.data.database.DatabaseRepository
import com.simplertutorials.android.wheathograophy.domain.City

class CityListPresenter(private val databaseRepository: DatabaseRepository) {


    fun getCurrentCityList(cityList: ArrayList<City>) {
        //get the up to date list from database
        cityList.clear()
        cityList.addAll(databaseRepository.cityList)
    }

    fun deleteCity(city: City) {
        databaseRepository.deleteCity(city)
    }

}
