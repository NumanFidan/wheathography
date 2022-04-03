package com.simplertutorials.android.wheathograophy.ui.fragments.cityListFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simplertutorials.android.wheathograophy.data.database.DatabaseRepository
import com.simplertutorials.android.wheathograophy.ui.fragments.BaseViewModel

class CityListViewModel(private val databaseRepository: DatabaseRepository) : BaseViewModel() {

    class Factory(private val databaseRepository: DatabaseRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CityListViewModel(databaseRepository) as T
        }
    }
}
