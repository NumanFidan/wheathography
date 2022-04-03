package com.simplertutorials.android.wheathograophy.components

import com.simplertutorials.android.wheathograophy.ui.fragments.addCityFragment.AddCityFragment
import javax.inject.Singleton
import com.simplertutorials.android.wheathograophy.ui.fragments.cityListFragment.CityListFragment
import com.simplertutorials.android.wheathograophy.ui.fragments.weatherInfoFragment.WeatherInfoFragment
import dagger.Component

@Singleton
@Component(modules = [ApiModule::class, StorageModule::class, ContextModule::class])
interface ApplicationComponent {
    fun inject(addCityFragment: AddCityFragment?)
    fun inject(cityListFragment: CityListFragment?)
    fun inject(weatherInfoFragment: WeatherInfoFragment?)
}
