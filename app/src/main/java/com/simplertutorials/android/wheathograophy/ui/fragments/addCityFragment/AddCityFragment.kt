package com.simplertutorials.android.wheathograophy.ui.fragments.addCityFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.simplertutorials.android.wheathograophy.databinding.CityAddFragmentBinding
import com.simplertutorials.android.wheathograophy.ui.fragments.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddCityFragment : BaseFragment<AddCityViewModel, CityAddFragmentBinding>() {

    override val viewModel by viewModel<AddCityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
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
        B.cityaddComposeview.setContent {
            AddCityView(
                viewModel::onAddCityClicked,
                viewModel::onCancelClicked
            )
        }
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CityAddFragmentBinding? =
        CityAddFragmentBinding.inflate(inflater, container, false)
}
