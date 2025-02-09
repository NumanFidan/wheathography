package com.simplertutorials.android.wheathograophy.koinComponents

import com.simplertutorials.android.wheathograophy.ui.fragments.addCityFragment.AddCityViewModel
import com.simplertutorials.android.wheathograophy.ui.fragments.cityListFragment.CityListViewModel
import com.simplertutorials.android.wheathograophy.ui.fragments.weatherInfoFragment.WeatherInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AddCityViewModel(get(), get()) }
    viewModel { CityListViewModel(get(), get()) }
    viewModel { WeatherInfoViewModel(get()) }
}