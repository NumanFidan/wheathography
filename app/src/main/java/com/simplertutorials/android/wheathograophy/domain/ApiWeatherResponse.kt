package com.simplertutorials.android.wheathograophy.domain

import com.google.gson.annotations.SerializedName

class ApiWeatherResponse {

    @SerializedName("main")
    var informationCube: InformationCube ?=null
    @SerializedName("weather")
    var weather = ArrayList<WeatherResponse>()
}

class WeatherResponse{
    @SerializedName("description")
    var description:String ?=null
}
class InformationCube {
    @SerializedName("temp")
    var temp:Float = 0.0f
    @SerializedName("humidity")
    var humidity:Float = 0.0f
}
