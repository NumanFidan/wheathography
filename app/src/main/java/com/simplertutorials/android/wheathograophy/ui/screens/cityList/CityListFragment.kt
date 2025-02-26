package com.simplertutorials.android.wheathograophy.ui.screens.cityList

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.databinding.CityListFragmentBinding
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.ui.screens.BaseFragment
import com.simplertutorials.android.wheathograophy.ui.screens.addCity.AddCityFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityListFragment : BaseFragment<CityListViewModel, CityListFragmentBinding>() {

    override val viewModel by viewModel<CityListViewModel>()

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi(view)
        observeUiEvents()
    }

    private fun observeUiEvents() {
        viewModel.getRequestWeatherInfoFragment()
            .observe { fragment ->
                activityCallback.launchFragment(fragment)
            }
        viewModel.getRequestDeleteConfirmationDialogLiveData()
            .observe {
                showDeleteConfirmationDialog(it)
            }
    }

    private fun updateUi(view: View) {
        B.composeView.setContent {
            CityListView(
                viewModel.getCityListLiveData(),
                viewModel.getIsRefreshingStateFlow(),
                viewModel::onCityClicked,
                { activityCallback.launchFragment(AddCityFragment()) },
                viewModel::cityListRefresh,
            )
        }
    }

    private fun onCityLongClicked(city: City) {
        viewModel.onCityLongClicked(city)
    }

    private fun showDeleteConfirmationDialog(city: City) {
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setMessage(getString(R.string.delete_the_city) + city.name)
        alertDialog.setTitle(getString(R.string.delete_city))
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.delete)) { _, _ ->
            viewModel.deleteCityConfirmed(city)
            alertDialog.dismiss()
        }
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.cancel)) { _, _ ->
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CityListFragmentBinding? =
        CityListFragmentBinding.inflate(inflater, container, false)
}
