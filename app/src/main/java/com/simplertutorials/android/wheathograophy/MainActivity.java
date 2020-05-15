package com.simplertutorials.android.wheathograophy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MainActiviryMVP.View{

    private MainActiviryMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActiviryPresenter();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
