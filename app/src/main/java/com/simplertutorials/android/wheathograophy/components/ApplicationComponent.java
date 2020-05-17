package com.simplertutorials.android.wheathograophy.components;

import com.simplertutorials.android.wheathograophy.ui.MainActivity;
import com.simplertutorials.android.wheathograophy.ui.fragments.AddCityFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, ContextModule.class})
public interface ApplicationComponent {


    void inject(AddCityFragment addCityFragment);

    void inject(MainActivity mainActivity);
}

