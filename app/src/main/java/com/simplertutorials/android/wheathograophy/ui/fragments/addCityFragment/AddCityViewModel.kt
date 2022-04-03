package com.simplertutorials.android.wheathograophy.ui.fragments.addCityFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simplertutorials.android.wheathograophy.data.database.DatabaseRepository
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.ui.fragments.BaseViewModel
import com.simplertutorials.android.wheathograophy.ui.fragments.cityListFragment.CityListFragment

class AddCityViewModel(
    private val databaseRepository: DatabaseRepository
) : BaseViewModel() {

    private val requestSnackBarLiveData: MutableLiveData<String> = MutableLiveData()
    private val requestCityListFragment: MutableLiveData<CityListFragment> = MutableLiveData()

    fun getRequestSnackBarLiveData(): LiveData<String> = requestSnackBarLiveData
    fun getRequestCityListFragment(): LiveData<CityListFragment> = requestCityListFragment

    fun saveCity(cityName: String) {
        //save the city, show the info snackBar and return to the CityListFragment
        val city = City(cityName)
        databaseRepository.addCity(city)
        requestSnackBarLiveData.value = "meessage"
        requestCityListFragment.value = CityListFragment()
    }

    class Factory(val databaseRepository: DatabaseRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AddCityViewModel(databaseRepository) as T
        }

    }
}
