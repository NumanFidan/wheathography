package com.simplertutorials.android.wheathograophy.domain

data class City(val name: String, val weather: Weather? = null) {

    override fun equals(other: Any?): Boolean {
        return other is City && name == other.name
    }
}
