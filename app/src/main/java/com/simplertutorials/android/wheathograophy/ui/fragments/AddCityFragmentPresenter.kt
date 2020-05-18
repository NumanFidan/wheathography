package com.simplertutorials.android.wheathograophy.ui.fragments

import com.simplertutorials.android.wheathograophy.data.database.DatabaseRepository2
import com.simplertutorials.android.wheathograophy.domain.City

class AddCityFragmentPresenter(private val databaseRepository: DatabaseRepository2,
                               private val view: AddCityFragment) {

    fun saveCity(cityName: String) {
        val city = City(cityName)
        databaseRepository.addCity(city)
        view.showSnackBar()
        view.returnToCityList()
    }
}