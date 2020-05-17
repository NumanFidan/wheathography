package com.simplertutorials.android.wheathograophy.data.api

import com.simplertutorials.android.wheathograophy.domain.ApiWeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET ("weather")
    fun getWeather(@Query("q")cityName:String,
                   @Query("appid")apiKey:String)
            : Single<ApiWeatherResponse>

}