package com.simplertutorials.android.wheathograophy.ui.customListeners

import com.simplertutorials.android.wheathograophy.domain.City

interface OnCityClickListener {
    fun onCityClicked(city: City)
    fun onCityLongClicked(city: City)
}