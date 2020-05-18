package com.simplertutorials.android.wheathograophy.domain;

import java.util.Comparator;

import androidx.annotation.Nullable;

public class City {
    private String name;
    private Weather weather;

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof City))
            return false;
        return this.name.equalsIgnoreCase(((City) obj).name);
    }
}
