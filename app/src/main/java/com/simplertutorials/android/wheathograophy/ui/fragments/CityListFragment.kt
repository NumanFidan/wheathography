package com.simplertutorials.android.wheathograophy.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simplertutorials.android.wheathograophy.MainApplication
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.data.database.DatabaseRepository
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.ui.MainActivity
import com.simplertutorials.android.wheathograophy.ui.adapters.CityListAdapter
import kotlinx.android.synthetic.main.city_list_fragment.view.*
import javax.inject.Inject

class CityListFragment: Fragment() {

//    @Inject
//    lateinit var databaseRepository: DatabaseRepository
    private lateinit var recyclerView: RecyclerView
    private lateinit var cityList: ArrayList<City>
    private lateinit var recylclerViewAdapter:CityListAdapter
    private lateinit var activity: MainActivity
    private lateinit var presenter: CityListPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        presenter = CityListPresenter(databaseRepository, this)
//        presenter.getCurrentCityList(cityList)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.city_list_fragment, container, false)
        updateUi(view)

        return view
    }

    private fun updateUi(view: View) {
        setUpRecyclerView(view)
        view.add_city_btn.setOnClickListener(View.OnClickListener {
            activity.changeFragment(R.id.content_main, AddCityFragment())
        })
    }

    private fun setUpRecyclerView(view: View) {

        val layoutManager = LinearLayoutManager(context);

        recylclerViewAdapter = CityListAdapter(cityList)
        view.city_list.apply {
            setHasFixedSize(true)
            setAdapter(adapter)
            this.layoutManager = layoutManager
        }
    }

    fun cityListRefresh(){
        recylclerViewAdapter.notifyDataSetChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as MainActivity;
//        (activity.applicationContext as MainApplication).component.inject(this)
    }

}