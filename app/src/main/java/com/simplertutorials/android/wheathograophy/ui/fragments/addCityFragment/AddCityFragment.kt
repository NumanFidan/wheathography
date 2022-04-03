package com.simplertutorials.android.wheathograophy.ui.fragments.addCityFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.data.database.DatabaseRepository
import com.simplertutorials.android.wheathograophy.ui.MainActivity
import com.simplertutorials.android.wheathograophy.ui.fragments.CityListFragment
import kotlinx.android.synthetic.main.city_add_fragment.*
import kotlinx.android.synthetic.main.city_add_fragment.view.*

class AddCityFragment: Fragment() {

    private lateinit var _presenter: AddCityFragmentPresenter
    private lateinit var databaseRepositoryCities: DatabaseRepository
    private lateinit var activity: MainActivity
    private var KEY:String = "Cities"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settings = requireContext().getSharedPreferences(KEY, 0)
        databaseRepositoryCities = DatabaseRepository.getInstance(settings, KEY)
        _presenter = AddCityFragmentPresenter(databaseRepositoryCities, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.city_add_fragment, container, false)
        updateUi(view)
        return view
    }

    private fun updateUi(view: View) {
        view.addcity_btn.setOnClickListener {
            val cityName = cityadd_text.text.toString()
            _presenter.saveCity(cityName)
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

}
