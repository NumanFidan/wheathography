package com.simplertutorials.android.wheathograophy.data.api

import com.simplertutorials.android.wheathograophy.BuildConfig
import com.simplertutorials.android.wheathograophy.domain.ApiWeatherResponse
import com.simplertutorials.android.wheathograophy.domain.City
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiRepository private constructor() {
    fun getWeatherInfo(apiService: ApiService, city: City): Observable<ApiWeatherResponse> {
        return apiService.getWeather(
            city.name,
            BuildConfig.apiKey
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
    }

    companion object {
        var instance: ApiRepository? = null
            get() {
                if (field == null) field = ApiRepository()
                return field
            }
            private set
    }
}
