package com.simplertutorials.android.wheathograophy.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class City implements Parcelable {
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
        //To compare two city use name
        if (!(obj instanceof City))
            return false;
        return this.name.equalsIgnoreCase(((City) obj).name);
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }

    public final static Parcelable.Creator<City> CREATOR = new Creator<City>() {
        @SuppressWarnings({
                "unchecked"
        })
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[0];
        }
    };

    protected City(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(getName());
    }
}
