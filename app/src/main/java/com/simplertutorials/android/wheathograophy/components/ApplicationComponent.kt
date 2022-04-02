package com.simplertutorials.android.wheathograophy.components

import javax.inject.Singleton
import com.simplertutorials.android.wheathograophy.ui.fragments.CityListFragment
import com.simplertutorials.android.wheathograophy.ui.fragments.WeatherInfoFragment
import dagger.Component

@Singleton
@Component(modules = [ApiModule::class, ContextModule::class])
interface ApplicationComponent {
    fun inject(cityListFragment: CityListFragment?)
    fun inject(weatherInfoFragment: WeatherInfoFragment?)
}
