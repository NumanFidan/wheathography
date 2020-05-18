package com.simplertutorials.android.wheathograophy.components;

import com.simplertutorials.android.wheathograophy.ui.MainActivity;
import com.simplertutorials.android.wheathograophy.ui.fragments.AddCityFragment;
import com.simplertutorials.android.wheathograophy.ui.fragments.CityListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, ContextModule.class, DatabaseModule.class})
public interface ApplicationComponent {


    void inject(AddCityFragment addCityFragment);

    void inject(CityListFragment cityListFragment);

    void inject(MainActivity mainActivity);
}

