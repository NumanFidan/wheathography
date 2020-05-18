package com.simplertutorials.android.wheathograophy.ui.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.data.api.ApiRepository
import com.simplertutorials.android.wheathograophy.data.api.ApiService
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.ui.fragments.OnCityClickListener
import kotlinx.android.synthetic.main.city_list_recyclerv_row.view.*


class CityListAdapter(private val cityListData: ArrayList<City>,
                      private val onCityClickListener: OnCityClickListener,
                      private val apiRepository: ApiRepository,
                      private val apiService: ApiService)
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

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onBindViewHolder(holder: CityListHolder, position: Int) {
        val city = cityListData[position]
        holder.cityName.text = city.name
        hideCityTemp(holder)

        holder.layout.setOnClickListener {
            onCityClickListener.onCityClicked(city)
        }

        holder.layout.setOnLongClickListener(OnLongClickListener {
            onCityClickListener.onCityLongClicked(city)
            true
        })

        apiRepository.getWeatherInfo(apiService, city)
                .subscribe({ n ->
                    holder.cityTemp.text = String.format("%.2f", n.informationCube!!.temp - 273.15) + "°C"
                    hideProgressbar(holder)
                },
                        { e ->
                            holder.cityTemp.text = "!"
                            hideProgressbar(holder)
                        })
    }

    private fun hideProgressbar(holder: CityListHolder) {
        holder.cityTemp.visibility = View.VISIBLE
        holder.loading.visibility = View.GONE
    }
    private fun hideCityTemp(holder: CityListHolder) {
        holder.cityTemp.visibility = View.GONE
        holder.loading.visibility = View.VISIBLE
    }

    class CityListHolder(val view: View, onCityClickListener: OnCityClickListener) : RecyclerView.ViewHolder(view) {

        val loading = view.progressBar as ProgressBar
        val cityName = view.row_cityname as TextView
        val cityTemp = view.row_cityweather as TextView
        val layout = view
    }


}