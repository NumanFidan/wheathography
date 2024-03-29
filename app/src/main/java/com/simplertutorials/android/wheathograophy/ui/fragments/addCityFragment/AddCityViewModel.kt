package com.simplertutorials.android.wheathograophy.ui.fragments.addCityFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.data.database.StorageRepository
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.managers.ResourceManager
import com.simplertutorials.android.wheathograophy.ui.fragments.BaseViewModel
import com.simplertutorials.android.wheathograophy.ui.fragments.cityListFragment.CityListFragment

class AddCityViewModel(
    private val storageRepository: StorageRepository,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    private val requestSnackBarLiveData: MutableLiveData<String> = MutableLiveData()
    private val requestCityListFragment: MutableLiveData<CityListFragment> = MutableLiveData()

    fun getRequestSnackBarLiveData(): LiveData<String> = requestSnackBarLiveData
    fun getRequestCityListFragment(): LiveData<CityListFragment> = requestCityListFragment

    fun onAddCityClicked(cityName: String) {
        saveCity(cityName)
    }

    fun onCancelClicked() {
        requestCityListFragment.value = CityListFragment()
    }

    private fun saveCity(cityName: String) {
        //save the city, show the info snackBar and return to the CityListFragment
        val city = City(cityName)
        storageRepository.addCity(city)
        requestSnackBarLiveData.value = resourceManager.getString(R.string.city_added, cityName)
        requestCityListFragment.value = CityListFragment()
    }

    class Factory(
        private val storageRepository: StorageRepository,
        private val resourceManager: ResourceManager
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AddCityViewModel(storageRepository, resourceManager) as T
        }
    }
}
