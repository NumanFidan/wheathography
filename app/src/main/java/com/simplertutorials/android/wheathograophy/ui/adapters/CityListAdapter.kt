package com.simplertutorials.android.wheathograophy.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simplertutorials.android.wheathograophy.R
import kotlinx.android.synthetic.main.city_list_recyclerv_row.view.*

class CityListAdapter(private val cityListData: ArrayList<String>)
    : RecyclerView.Adapter<CityListAdapter.CityListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CityListAdapter.CityListHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.city_list_recyclerv_row, parent, false)
        return CityListHolder(view)
    }

    override fun getItemCount(): Int {
        return cityListData.size
    }

    override fun onBindViewHolder(holder: CityListHolder, position: Int) {
        holder.view.row_cityname.text = cityListData[position]
    }


    class CityListHolder(val view: View) : RecyclerView.ViewHolder(view){
        init {

            view.setOnClickListener(View.OnClickListener {
                (it.row_cityname as TextView).text = "selected"
            })
        }
    }


}