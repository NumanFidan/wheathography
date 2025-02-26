package com.simplertutorials.android.wheathograophy.ui.screens.weatherInfo

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.databinding.WeatherInfoFragmentBinding
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.ui.screens.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.compose.runtime.getValue

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
        updateUI()
    }

    private fun updateUI() {
        B.composeView.setContent {
            val city by viewModel.getUpdateFieldsLiveData().collectAsStateWithLifecycle(
                City("Default", null)
            )
            WeatherInfoView(
                cityName = city.name,
                temperature = city.weather?.currentTemp,
                humidity = city.weather?.humidity,
                weatherDescription = city.weather?.description
            )
        }
    }

    private fun observeUiEvents() {
        viewModel.getRequestErrorDialog()
            .observe { showErrorDialog(it) }
        viewModel.getRequestCityListFragment()
            .observe { activityCallback.launchFragment(it) }
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
