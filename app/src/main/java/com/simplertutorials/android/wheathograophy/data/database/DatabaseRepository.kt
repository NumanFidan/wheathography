package com.simplertutorials.android.wheathograophy.data.database

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.simplertutorials.android.wheathograophy.domain.City

class DatabaseRepository @SuppressLint("CommitPrefEdits") private constructor(
    private val settings: SharedPreferences,
    key: String
) {
    private val editor: SharedPreferences.Editor = settings.edit()
    private val manager: DatabaseManager = DatabaseManager(editor, settings)
    private val KEY: String = key
    val cityList: List<City>
        get() = convertToCityObject(manager.readSet(KEY))

    fun addCity(city: City) {
        val cityList = mutableListOf<City>()
        cityList.addAll(cityList)
        if (!cityList.contains(city)) {
            cityList.add(city)
            rewriteSharedPreferences(cityList)
        }
    }

    fun deleteCity(city: City?) {
        val cityList = mutableListOf<City>()
        cityList.addAll(cityList)
        if (cityList.contains(city)) {
            cityList.remove(city)
            rewriteSharedPreferences(cityList)
        }
    }

    private fun rewriteSharedPreferences(cityList: List<City>) {
        manager.deleteValue(KEY)
        manager.writeSet(convertToHashSet(cityList), KEY)
    }

    private fun convertToHashSet(cityList: List<City>): HashSet<String> {
        val hashSet = HashSet<String>()
        for (it in cityList) {
            hashSet.add(it.name)
        }
        return hashSet
    }

    private fun convertToCityObject(readSet: Set<String>?): List<City> {
        val cityList = mutableListOf<City>()
        if (readSet != null) {
            for (it in readSet) {
                val city = City(it)
                cityList.add(city)
            }
        }
        return cityList
    }

    companion object {
        private var instance: DatabaseRepository? = null
        fun getInstance(settings: SharedPreferences, key: String): DatabaseRepository {
            if (instance == null) instance = DatabaseRepository(settings, key)
            return instance!!
        }
    }

}
