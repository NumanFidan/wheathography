package com.simplertutorials.android.wheathograophy.ui;

import com.simplertutorials.android.wheathograophy.ui.fragments.CityListFragment;

import androidx.fragment.app.Fragment;

public class MainActivityPresenter implements MainActivityMVP.Presenter {

    private CityListFragment cityListFragment;


    @Override
    public void attachFragment(Fragment fragment) {
        if (fragment instanceof CityListFragment){
            this.cityListFragment = (CityListFragment) fragment;
        }
    }
}
