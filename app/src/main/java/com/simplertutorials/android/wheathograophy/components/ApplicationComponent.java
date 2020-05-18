package com.simplertutorials.android.wheathograophy.components;

import com.simplertutorials.android.wheathograophy.ui.fragments.CityListFragment;
import com.simplertutorials.android.wheathograophy.ui.fragments.WeatherInfoFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, ContextModule.class})
public interface ApplicationComponent {

    void inject(CityListFragment cityListFragment);

    void inject(WeatherInfoFragment weatherInfoFragment);

}

