package com.simplertutorials.android.wheathograophy.ui.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.simplertutorials.android.wheathograophy.MainApplication
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.data.api.ApiService
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.ui.MainActivity
import kotlinx.android.synthetic.main.weather_info_fragment.view.*
import javax.inject.Inject

class WeatherInfoFragment : Fragment() {

    @Inject
    lateinit var apiService: ApiService
    private lateinit var currentCity: City
    private val ARG_CITY_PARAM: String = "current_city"
    private lateinit var _presenter: WeatherInfoPresenter
    private lateinit var activity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            currentCity = arguments!!.getParcelable(ARG_CITY_PARAM)!!
        }

        (activity.applicationContext as MainApplication).component.inject(this)
        _presenter = WeatherInfoPresenter(this, apiService)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.weather_info_fragment, container, false)
        updateUi(view)
        return view
    }

    private fun updateUi(view: View) {
        //fetch the weather Info from API and update UI
        _presenter.fetchCityWeather(currentCity, view)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }

    @SuppressLint("SetTextI18n")
    fun updateFields(currentCity: City, view: View) {
        view.city_name.text = currentCity.name
        view.humidity.text = currentCity.weather.humidity
        view.temprature.text = currentCity.weather.currentTemp + "°C"
        view.description.text = currentCity.weather.description
    }

    fun showErrorDialog(e: Throwable?) {
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setMessage(e?.message)
        alertDialog.setTitle(getString(R.string.we_face_with_an_error))
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.ok)
        ) { _, _ ->
            alertDialog.dismiss()
            activity.changeFragment(R.id.content_main, CityListFragment())
        }
        alertDialog.show()
    }

}