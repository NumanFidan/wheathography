package com.simplertutorials.android.wheathograophy.data.api;

import com.simplertutorials.android.wheathograophy.BuildConfig;
import com.simplertutorials.android.wheathograophy.domain.ApiWeatherResponse;
import com.simplertutorials.android.wheathograophy.domain.City;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiRepository {
    private static ApiRepository instance;


    private ApiRepository() {

    }

    public static ApiRepository getInstance() {
        if (instance == null)
            instance = new ApiRepository();
        return instance;
    }

    public Observable<ApiWeatherResponse> getWeatherInfo(ApiService apiService, City city) {
        return apiService.getWeather(city.getName(),
                BuildConfig.apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable();

    }
}
