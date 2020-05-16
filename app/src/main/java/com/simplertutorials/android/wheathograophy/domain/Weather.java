package com.simplertutorials.android.wheathograophy.domain;

import android.security.keystore.StrongBoxUnavailableException;

public class Weather {
    private String currentTemp;
    private String humidity;
    private String description;

    public String getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
