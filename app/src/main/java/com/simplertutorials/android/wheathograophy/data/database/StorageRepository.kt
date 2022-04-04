package com.simplertutorials.android.wheathograophy.data.database

import com.simplertutorials.android.wheathograophy.domain.City

class StorageRepository(private val manager: SharedPreferencesManager, private val key: String) {
    val cityList: List<City>
        get() = convertToCityObject(manager.readSet(key))

    fun addCity(city: City) {
        if (!cityList.contains(city)) {
            rewriteSharedPreferences(cityList + listOf(city))
        }
    }

    fun deleteCity(city: City?) {
        if (cityList.contains(city)) {
            rewriteSharedPreferences(cityList.filterNot { it == city })
        }
    }

    private fun rewriteSharedPreferences(cityList: List<City>) {
        manager.deleteValue(key)
        manager.writeSet(convertToHashSet(cityList), key)
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
}
