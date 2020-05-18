package com.simplertutorials.android.wheathograophy.domain

class Weather {
    var currentTemp: String? = null
    var humidity: String? = null
    var description: String? = null

    constructor(currentTemp: String?, humidity: String?, description: String?) {
        this.currentTemp = currentTemp
        this.humidity = humidity
        this.description = description
    }

    constructor()
}