package com.simplertutorials.android.wheathograophy.ui.fragments

import com.simplertutorials.android.wheathograophy.data.database.DatabaseRepository2
import com.simplertutorials.android.wheathograophy.domain.City

class CityListPresenter(private val databaseRepository: DatabaseRepository2,
                        private val view: CityListFragment) {


    fun getCurrentCityList(cityList: ArrayList<City>) {
        cityList.clear()
        cityList.addAll(databaseRepository.getCityList())
    }

    fun deleteCity(city: City) {
        databaseRepository.deleteCity(city)
    }

}