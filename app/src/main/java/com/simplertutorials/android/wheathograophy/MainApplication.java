package com.simplertutorials.android.wheathograophy;

import android.app.Application;

import com.simplertutorials.android.wheathograophy.components.ApplicationComponent;
import com.simplertutorials.android.wheathograophy.components.ContextModule;
//import com.simplertutorials.android.wheathograophy.components.DaggerApplicationComponent;

import java.util.stream.DoubleStream;

public class MainApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

//        applicationComponent = DaggerApplicationComponent.builder()
//                .contextModule(new ContextModule(this))
//                .build();
    }
    public ApplicationComponent getComponent(){
        return applicationComponent;
    }
}
