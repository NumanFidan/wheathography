package com.simplertutorials.android.wheathograophy.data.database

import com.simplertutorials.android.wheathograophy.domain.City

class DatabaseRepository(private var databaseManager: DatabaseManager) {

    private var CITIES_Key = "cities"


    fun getCityList(): ArrayList<City> {
        return convertToCityObject(databaseManager.readSet(CITIES_Key))
    }

    fun addCity(city: City) {
        val cityList = getCityList()
        if (!(cityList.contains(city))) {
            cityList.add(city)
            rewriteSharedPreferences(cityList)
        }
    }

    fun deleteCity(city: City) {
        val cityList = getCityList()
        if (cityList.contains(city)) {
            cityList.remove(city)
            rewriteSharedPreferences(cityList)
        }
    }

    private fun rewriteSharedPreferences(cityList: java.util.ArrayList<City>) {
        databaseManager.deleteValue(CITIES_Key)
        databaseManager.writeSet(convertToHashSet(cityList), CITIES_Key)
    }

    private fun convertToHashSet(cityList: ArrayList<City>): HashSet<String> {
        val hashSet = HashSet<String>()
        cityList.forEach {
            hashSet.add(it.name)
        }
        return hashSet
    }

    private fun convertToCityObject(readSet: MutableSet<String>?): ArrayList<City> {
        val cityList = ArrayList<City>()
        if (readSet != null) {
            readSet.forEach {
                val city = City(it)
                cityList.add(city)
            }
        }
        return cityList
    }

}