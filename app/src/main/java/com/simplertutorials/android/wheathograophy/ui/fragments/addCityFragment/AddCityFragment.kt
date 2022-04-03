package com.simplertutorials.android.wheathograophy.ui.fragments.addCityFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.simplertutorials.android.wheathograophy.MainApplication
import com.simplertutorials.android.wheathograophy.data.database.StorageRepository
import com.simplertutorials.android.wheathograophy.databinding.CityAddFragmentBinding
import com.simplertutorials.android.wheathograophy.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.city_add_fragment.*
import javax.inject.Inject

class AddCityFragment : BaseFragment<AddCityViewModel, CityAddFragmentBinding>() {

    @Inject
    lateinit var storageRepositoryCities: StorageRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().applicationContext as MainApplication).component?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        observeUiEvents()
    }

    private fun observeUiEvents() {
        viewModel.getRequestSnackBarLiveData()
            .observe {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
            }
        viewModel.getRequestCityListFragment()
            .observe {
                activityCallback.launchFragment(it)
            }
    }

    private fun setUpUI() {
        B.addcityBtn.setOnClickListener {
            val cityName = cityadd_text.text.toString()
            viewModel.onAddCityClicked(cityName)
        }
        B.cancelBtn.setOnClickListener {
            viewModel.onCancelClicked()
        }
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CityAddFragmentBinding? =
        CityAddFragmentBinding.inflate(inflater, container, false)

    override fun generateViewModel(): AddCityViewModel =
        ViewModelProvider(
            this, AddCityViewModel.Factory(
                storageRepositoryCities
            )
        ).get(AddCityViewModel::class.java)

}
