package com.simplertutorials.android.wheathograophy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;

import com.simplertutorials.android.wheathograophy.R;
import com.simplertutorials.android.wheathograophy.ui.fragments.CityListFragment;

public class MainActivity extends AppCompatActivity implements MainActiviryMVP.View {

    public MainActiviryMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter();

        changeFragment(R.id.content_main, new CityListFragment());
    }

    @Override
    public void changeFragment(int containerId, Fragment fragment) {
        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
        ft.replace(containerId, fragment);
        ft.commit();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
