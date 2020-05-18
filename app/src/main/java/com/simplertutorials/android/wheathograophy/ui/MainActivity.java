package com.simplertutorials.android.wheathograophy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.simplertutorials.android.wheathograophy.MainApplication;
import com.simplertutorials.android.wheathograophy.R;
import com.simplertutorials.android.wheathograophy.data.api.ApiService;
import com.simplertutorials.android.wheathograophy.domain.ApiWeatherResponse;
import com.simplertutorials.android.wheathograophy.ui.fragments.AddCityFragment;
import com.simplertutorials.android.wheathograophy.ui.fragments.CityListFragment;
import com.simplertutorials.android.wheathograophy.ui.fragments.WeatherInfoFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityMVP.View {

    public MainActivityMVP.Presenter presenter;

    @Inject
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MainApplication) getApplicationContext()).getComponent().inject(this);
        presenter = new MainActivityPresenter();

        changeFragment(R.id.content_main, new CityListFragment());
    }

    @Override
    public void changeFragment(int containerId, Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(containerId, fragment);
        ft.commit();
        presenter.attachFragment(fragment);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void onBackPressed() {
        //When user press the back button, we will always turn back to the city list
        //If we are already at the city list, then we will close the app
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_main);
        if (fragment instanceof WeatherInfoFragment || fragment instanceof AddCityFragment)
            changeFragment(R.id.content_main, new CityListFragment());
        else
            super.onBackPressed();
    }

}
