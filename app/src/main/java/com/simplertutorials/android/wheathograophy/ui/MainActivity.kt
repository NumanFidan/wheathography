package com.simplertutorials.android.wheathograophy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.ui.fragments.cityListFragment.CityListFragment
import com.simplertutorials.android.wheathograophy.ui.fragments.weatherInfoFragment.WeatherInfoFragment
import com.simplertutorials.android.wheathograophy.ui.fragments.addCityFragment.AddCityFragment

class MainActivity : AppCompatActivity(), ActivityCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment()
    }

    private fun loadFragment() {
        //If this is the first time app start, load cityListFragment
        //if onCreate called for another reason(orientation change) keep current Fragment
        if (supportFragmentManager.findFragmentById(R.id.content_main) == null) {
            launchFragment(CityListFragment())
        }
    }

    override fun onBackPressed() {
        //When user press the back button, we will always turn back to the city list
        //If we are already at the city list, then we will close the app
        val fragment = supportFragmentManager.findFragmentById(R.id.content_main)
        if (fragment is WeatherInfoFragment || fragment is AddCityFragment) {
            launchFragment(CityListFragment())
        } else {
            super.onBackPressed()
        }
    }

    override fun launchFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.content_main, fragment)
        ft.commit()
    }
}
