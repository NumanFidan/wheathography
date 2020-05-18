package com.simplertutorials.android.wheathograophy.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.simplertutorials.android.wheathograophy.MainApplication
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.data.api.ApiRepository
import com.simplertutorials.android.wheathograophy.data.api.ApiService
import com.simplertutorials.android.wheathograophy.data.database.DatabaseRepository2
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.ui.MainActivity
import com.simplertutorials.android.wheathograophy.ui.adapters.CityListAdapter
import kotlinx.android.synthetic.main.city_list_fragment.view.*
import javax.inject.Inject

class CityListFragment : Fragment(), OnCityClickListener {

    @Inject
    lateinit var apiService: ApiService

    private var KEY: String = "Cities"
    private val ARG_CITY_PARAM: String = "current_city"

    private lateinit var swipeToRefreshLayout: SwipeRefreshLayout
    private lateinit var cityList: ArrayList<City>
    private lateinit var recylclerViewAdapter: CityListAdapter
    private lateinit var activity: MainActivity
    private lateinit var presenter: CityListPresenter
    private lateinit var databaseRepository: DatabaseRepository2
    private lateinit var apiRepository: ApiRepository



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settings = context?.getSharedPreferences(KEY, 0)
        databaseRepository = DatabaseRepository2.getInstance(settings, KEY)
        presenter = CityListPresenter(databaseRepository, this)
        cityList = ArrayList<City>()
        presenter.getCurrentCityList(cityList)

        apiRepository = ApiRepository.getInstance()
        (activity.applicationContext as MainApplication).component.inject(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.city_list_fragment, container, false)
        updateUi(view)
        return view
    }

    private fun updateUi(view: View) {
        view.add_city_btn.setOnClickListener(View.OnClickListener {
            activity.changeFragment(R.id.content_main, AddCityFragment())
        })
        setUpRecyclerView(view)
        setUpSwipeToRefresh(view)
    }

    private fun setUpSwipeToRefresh(view: View) {
        swipeToRefreshLayout = view.swipetorefresh_layout
        swipeToRefreshLayout.setOnRefreshListener {
            cityListRefresh()
            swipeToRefreshLayout.isRefreshing = false
        }
    }

    private fun setUpRecyclerView(view: View) {

        val layoutManager = LinearLayoutManager(context)

        recylclerViewAdapter = CityListAdapter(cityList, this, apiRepository, apiService )
        view.city_list.apply {
            setHasFixedSize(true)
            setAdapter(recylclerViewAdapter)
            this.layoutManager = layoutManager
        }
    }

    fun cityListRefresh() {
        presenter.getCurrentCityList(cityList)
        recylclerViewAdapter.notifyDataSetChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as MainActivity
    }

    override fun onCityClicked(city: City) {
        val fragment = WeatherInfoFragment()
        val args = Bundle()
        args.putParcelable(ARG_CITY_PARAM, city)
        fragment.arguments = args
        activity.changeFragment(R.id.content_main, fragment)
    }

    override fun onCityLongClicked(city: City) {
        showDeleteConfirmationDialog(city)
    }

    private fun showDeleteConfirmationDialog(city: City) {
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setMessage(getString(R.string.delete_the_city) + city.name)
        alertDialog.setTitle(getString(R.string.delete_city))
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.delete)) { _, _ ->
            presenter.deleteCity(city)
            alertDialog.dismiss()
            cityListRefresh()
        }
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.cancel)) { _, _ ->
            alertDialog.dismiss()
        }
        alertDialog.show()
    }
}