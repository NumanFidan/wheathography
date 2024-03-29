package com.simplertutorials.android.wheathograophy.data.api

import com.simplertutorials.android.wheathograophy.BuildConfig
import com.simplertutorials.android.wheathograophy.domain.ApiWeatherResponse
import com.simplertutorials.android.wheathograophy.domain.City
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiRepository constructor(private val apiService: ApiService) {

    fun getWeatherInfo(city: City): Observable<ApiWeatherResponse> {
        return apiService.getWeather(
            city.name,
            BuildConfig.apiKey
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
    }
}
