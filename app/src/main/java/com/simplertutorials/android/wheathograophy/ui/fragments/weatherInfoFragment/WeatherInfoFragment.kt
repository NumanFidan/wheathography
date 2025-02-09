package com.simplertutorials.android.wheathograophy.ui.fragments.weatherInfoFragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.databinding.WeatherInfoFragmentBinding
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.ui.fragments.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherInfoFragment : BaseFragment<WeatherInfoViewModel, WeatherInfoFragmentBinding>() {

    override val viewModel by viewModel<WeatherInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentCity = requireArguments().get(ARG_CITY_PARAM) as City
        viewModel.passArguments(currentCity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUiEvents()
    }

    private fun observeUiEvents() {
        viewModel.getRequestErrorDialog()
            .observe { showErrorDialog(it) }
        viewModel.getUpdateFieldsLiveData()
            .observe { updateFields(it) }
        viewModel.getRequestCityListFragment()
            .observe { activityCallback.launchFragment(it) }
    }

    @SuppressLint("SetTextI18n")
    private fun updateFields(currentCity: City) {
        B.cityName.text = currentCity.name
        B.humidity.text = currentCity.weather?.humidity
        B.temprature.text = getString(R.string.temp_with_celsius, currentCity.weather?.currentTemp)
        B.description.text = currentCity.weather?.description
    }

    private fun showErrorDialog(message: String) {
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setMessage(message)
        alertDialog.setTitle(getString(R.string.we_face_with_an_error))
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, getString(R.string.ok)
        ) { _, _ ->
            alertDialog.dismiss()
            viewModel.errorDialogClosed()
        }
        alertDialog.show()
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): WeatherInfoFragmentBinding? = WeatherInfoFragmentBinding.inflate(inflater, container, false)

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
}
