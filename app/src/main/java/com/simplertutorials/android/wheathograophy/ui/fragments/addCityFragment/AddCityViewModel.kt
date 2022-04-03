package com.simplertutorials.android.wheathograophy.ui.fragments.addCityFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simplertutorials.android.wheathograophy.data.database.DatabaseRepository
import com.simplertutorials.android.wheathograophy.ui.fragments.BaseViewModel

class AddCityViewModel(
    private val databaseRepository: DatabaseRepository
) : BaseViewModel() {

    class Factory(val databaseRepository: DatabaseRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AddCityViewModel(databaseRepository) as T
        }

    }
}
