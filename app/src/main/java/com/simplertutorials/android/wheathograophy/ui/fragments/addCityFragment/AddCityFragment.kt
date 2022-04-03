package com.simplertutorials.android.wheathograophy.ui.fragments.addCityFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.data.database.StorageRepository
import com.simplertutorials.android.wheathograophy.databinding.CityAddFragmentBinding
import com.simplertutorials.android.wheathograophy.ui.MainActivity
import com.simplertutorials.android.wheathograophy.ui.fragments.BaseFragment
import com.simplertutorials.android.wheathograophy.ui.fragments.cityListFragment.CityListFragment
import kotlinx.android.synthetic.main.city_add_fragment.*
import kotlinx.android.synthetic.main.city_add_fragment.view.*
import javax.inject.Inject

class AddCityFragment : BaseFragment<AddCityViewModel, CityAddFragmentBinding>() {

    @Inject
    lateinit var storageRepositoryCities: StorageRepository
    private lateinit var activity: MainActivity
    private var KEY: String = "Cities"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.city_add_fragment, container, false)
        updateUi(view)
        return view
    }

    private fun updateUi(view: View) {
        view.addcity_btn.setOnClickListener {
            val cityName = cityadd_text.text.toString()
            viewModel.saveCity(cityName)
        }

        view.cancel_btn.setOnClickListener {
            activity.changeFragment(R.id.content_main, CityListFragment())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as MainActivity;
    }

    fun showSnackBar() {
        Snackbar.make(requireView(), "City Added", Snackbar.LENGTH_SHORT).show()
    }

    fun returnToCityList() {
        activity.changeFragment(R.id.content_main, CityListFragment())
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
