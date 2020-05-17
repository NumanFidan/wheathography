package com.simplertutorials.android.wheathograophy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.simplertutorials.android.wheathograophy.BuildConfig;
import com.simplertutorials.android.wheathograophy.MainApplication;
import com.simplertutorials.android.wheathograophy.R;
import com.simplertutorials.android.wheathograophy.data.api.ApiService;
import com.simplertutorials.android.wheathograophy.domain.ApiWeatherResponse;
import com.simplertutorials.android.wheathograophy.ui.fragments.CityListFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityMVP.View {

    public MainActivityMVP.Presenter presenter;

    @Inject
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MainApplication)getApplicationContext()).getComponent().inject(this);
        test();
        presenter = new MainActivityPresenter();

        changeFragment(R.id.content_main, new CityListFragment());
    }

    private void test() {
        apiService.getWeather("Berlin",
                "c762ded84f0ef4ba014105bd7091b445")
                //Scheduler
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(
                        new Observer<ApiWeatherResponse>(){

                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ApiWeatherResponse apiWeatherResponse) {
                                Log.w("Result", String.valueOf(apiWeatherResponse.getInformationCube().getTemp()-273.15));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }


    @Override
    public void changeFragment(int containerId, Fragment fragment) {
        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
        ft.replace(containerId, fragment);
        ft.commit();
        presenter.attachFragment(fragment);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
