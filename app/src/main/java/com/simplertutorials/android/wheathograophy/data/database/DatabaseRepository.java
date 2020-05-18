package com.simplertutorials.android.wheathograophy.data.database;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.simplertutorials.android.wheathograophy.domain.City;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DatabaseRepository {
    private static DatabaseRepository instance;
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private DatabaseManager manager;
    private String KEY;

    @SuppressLint("CommitPrefEdits")
    private DatabaseRepository(SharedPreferences settings, String key) {
        this.settings = settings;
        this.editor = settings.edit();
        this.manager = new DatabaseManager(editor,settings);
        this.KEY = key;
    }

    public static DatabaseRepository getInstance(SharedPreferences settings, String key) {
        if (instance == null)
            instance = new DatabaseRepository(settings, key);
        return instance;
    }

    public ArrayList<City>getCityList(){
        return convertToCityObject(manager.readSet(KEY));
    }

    public void addCity(City city) {
        ArrayList cityList = getCityList();
        if (!(cityList.contains(city))) {
            cityList.add(city);
            rewriteSharedPreferences(cityList);
        }
    }

    public void deleteCity(City city) {
        ArrayList cityList = getCityList();
        if (cityList.contains(city)) {
            cityList.remove(city);
            rewriteSharedPreferences(cityList);
        }
    }

    private void rewriteSharedPreferences(ArrayList<City> cityList) {
        manager.deleteValue(KEY);
        manager.writeSet(convertToHashSet(cityList), KEY);
    }

    private HashSet<String> convertToHashSet(ArrayList<City> cityList){
        HashSet<String> hashSet = new HashSet<String>();
        for (City it: cityList) {
            hashSet.add(it.getName());
        }
        return hashSet;
    }

    private ArrayList<City> convertToCityObject(Set<String> readSet){
        ArrayList cityList = new ArrayList<City>();

        if (readSet != null) {
            for (String it : readSet){
                City city = new City(it);
                cityList.add(city);
            }
        }
        return cityList;
    }

}
