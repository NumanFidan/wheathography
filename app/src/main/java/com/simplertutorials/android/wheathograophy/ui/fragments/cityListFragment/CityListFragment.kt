package com.simplertutorials.android.wheathograophy.ui.fragments.cityListFragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.simplertutorials.android.wheathograophy.MainApplication
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.data.api.ApiRepository
import com.simplertutorials.android.wheathograophy.data.database.StorageRepository
import com.simplertutorials.android.wheathograophy.databinding.CityListFragmentBinding
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.ui.adapters.CityListAdapter
import com.simplertutorials.android.wheathograophy.ui.fragments.BaseFragment
import com.simplertutorials.android.wheathograophy.ui.fragments.addCityFragment.AddCityFragment
import javax.inject.Inject

class CityListFragment : BaseFragment<CityListViewModel, CityListFragmentBinding>() {

    @Inject
    lateinit var apiRepository: ApiRepository

    @Inject
    lateinit var storageRepository: StorageRepository
    private lateinit var swipeToRefreshLayout: SwipeRefreshLayout
    private lateinit var recylclerViewAdapter: CityListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().applicationContext as MainApplication).component?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi(view)
        observeUiEvents()
        setOnclickListeners()
    }

    private fun observeUiEvents() {
        viewModel.getRequestWeatherInfoFragment()
            .observe { fragment ->
                activityCallback.launchFragment(fragment)
            }
        viewModel.getRequestRefreshLiveData()
            .observe {
                swipeToRefreshLayout.isRefreshing = it
            }
        viewModel.getCityListLiveData()
            .observe {
                recylclerViewAdapter.setData(it)
            }
        viewModel.getRequestDeleteConfirmationDialogLiveData()
            .observe {
                showDeleteConfirmationDialog(it)
            }
    }

    private fun setOnclickListeners() {
        B.addCityBtn.setOnClickListener {
            activityCallback.launchFragment(AddCityFragment())
        }
    }

    private fun updateUi(view: View) {
        setUpRecyclerView()
        setUpSwipeToRefresh()
    }

    private fun setUpSwipeToRefresh() {
        swipeToRefreshLayout = B.swipetorefreshLayout
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.cityListRefresh()
            swipeToRefreshLayout.isRefreshing = false
        }
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        recylclerViewAdapter =
            CityListAdapter(requireContext(), ::onCityClicked, ::onCityLongClicked)
        B.cityList.apply {
            setHasFixedSize(true)
            adapter = recylclerViewAdapter
            this.layoutManager = layoutManager
        }
    }

    private fun onCityClicked(city: City) {
        viewModel.onCityClicked(city)
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

    override fun generateViewModel(): CityListViewModel =
        ViewModelProvider(
            this,
            CityListViewModel.Factory(storageRepository, apiRepository)
        ).get(CityListViewModel::class.java)
}
