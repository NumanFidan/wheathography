package com.simplertutorials.android.wheathograophy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.simplertutorials.android.wheathograophy.R

class AddCityFragment: Fragment() {

    private lateinit var _recyclerView: RecyclerView
    private lateinit var _presenter: CityListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _presenter = CityListPresenter()
        _presenter.getCurrentCityList()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.city_add_fragment, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun updateUi(view: View?) {

    }
}