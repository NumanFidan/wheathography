package com.simplertutorials.android.wheathograophy.ui.fragments

import com.simplertutorials.android.wheathograophy.data.database.DatabaseRepository
import com.simplertutorials.android.wheathograophy.domain.City

class AddCityFragmentPresenter(private val databaseRepository: DatabaseRepository,
                               private val view: AddCityFragment) {

    fun saveCity(cityName: String) {
        //save the city, show the info snackBar and return to the CityListFragment
        val city = City(cityName)
        databaseRepository.addCity(city)
        view.showSnackBar()
        view.returnToCityList()
    }
}