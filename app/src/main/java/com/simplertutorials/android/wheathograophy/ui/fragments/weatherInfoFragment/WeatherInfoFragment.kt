package com.simplertutorials.android.wheathograophy.ui.fragments.weatherInfoFragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.simplertutorials.android.wheathograophy.MainApplication
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.data.api.ApiRepository
import com.simplertutorials.android.wheathograophy.data.api.ApiService
import com.simplertutorials.android.wheathograophy.databinding.WeatherInfoFragmentBinding
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.ui.MainActivity
import com.simplertutorials.android.wheathograophy.ui.fragments.BaseFragment
import com.simplertutorials.android.wheathograophy.ui.fragments.cityListFragment.CityListFragment
import kotlinx.android.synthetic.main.weather_info_fragment.view.*
import javax.inject.Inject

class WeatherInfoFragment : BaseFragment<WeatherInfoViewModel, WeatherInfoFragmentBinding>() {

    @Inject
    lateinit var apiRepository: ApiRepository
    private lateinit var currentCity: City
    private lateinit var activity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            currentCity = requireArguments().get(ARG_CITY_PARAM) as City
        }

        (activity.applicationContext as MainApplication).component?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.weather_info_fragment, container, false)
        updateUi(view)
        return view
    }

    private fun updateUi(view: View) {
        //fetch the weather Info from API and update UI
        viewModel.fetchCityWeather(currentCity, view)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }

    @SuppressLint("SetTextI18n")
    fun updateFields(currentCity: City, view: View) {
        view.city_name.text = currentCity.name
        view.humidity.text = currentCity.weather?.humidity
        view.temprature.text = currentCity.weather?.currentTemp + "°C"
        view.description.text = currentCity.weather?.description
    }

    fun showErrorDialog(e: Throwable?) {
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setMessage(e?.message)
        alertDialog.setTitle(getString(R.string.we_face_with_an_error))
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, getString(R.string.ok)
        ) { _, _ ->
            alertDialog.dismiss()
            activity.changeFragment(R.id.content_main, CityListFragment())
        }
        alertDialog.show()
    }

    companion object {
        private const val ARG_CITY_PARAM: String = "current_city"
        fun newInstance(city: City): WeatherInfoFragment {
            return WeatherInfoFragment().apply {
                arguments = bundleOf(
                    ARG_CITY_PARAM to city
                )
            }
        }
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): WeatherInfoFragmentBinding? = WeatherInfoFragmentBinding.inflate(inflater, container, false)

    override fun generateViewModel(): WeatherInfoViewModel =
        ViewModelProvider(this, WeatherInfoViewModel.Factory(apiRepository)).get(
            WeatherInfoViewModel::class.java
        )

}
