package com.simplertutorials.android.wheathograophy.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.ui.fragments.OnCityClickListener
import kotlinx.android.synthetic.main.city_list_recyclerv_row.view.*


class CityListAdapter(private val cityListData: ArrayList<City>,
                      private val onCityClickListener: OnCityClickListener)
    : RecyclerView.Adapter<CityListAdapter.CityListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CityListAdapter.CityListHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.city_list_recyclerv_row, parent, false)
        return CityListHolder(view, onCityClickListener)
    }

    override fun getItemCount(): Int {
        return cityListData.size
    }

    override fun onBindViewHolder(holder: CityListHolder, position: Int) {
        val city = cityListData[position]
        holder.cityName.text = city.name
        holder.layout.setOnClickListener {
            onCityClickListener.onCityClicked(city)
        }

        holder.layout.setOnLongClickListener(OnLongClickListener {
            onCityClickListener.onCityLongClicked(city)
            true
        })
    }


    class CityListHolder(val view: View, onCityClickListener: OnCityClickListener) : RecyclerView.ViewHolder(view) {

        val cityName = view.row_cityname as TextView
        val layout = view
    }


}