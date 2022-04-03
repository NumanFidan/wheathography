package com.simplertutorials.android.wheathograophy.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.domain.City
import com.simplertutorials.android.wheathograophy.domain.Weather
import kotlinx.android.synthetic.main.city_list_recyclerv_row.view.*

class CityListAdapter(
    context: Context,
    private val onCityClicked: (City) -> Unit,
    private val onCityLongClicked: (City) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    private var cityListData: List<City> = emptyList()

    fun setData(cityList: List<City>) {
        this.cityListData = cityList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = layoutInflater.inflate(R.layout.city_list_recyclerv_row, parent, false)
        return CityListHolder(view)
    }

    override fun getItemCount(): Int {
        return cityListData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CityListHolder).onBind(cityListData[position])
    }

    private inner class CityListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val loadingProgressBar = itemView.progressBar as ProgressBar
        val cityName = itemView.row_cityname as TextView
        val cityTemp = itemView.row_cityweather as TextView
        val rootLayout = itemView

        fun onBind(city: City) {
            cityName.text = city.name
            cityTemp.text = city.weather?.currentTemp?.let { "$it°C" } ?: "!"
            when (city.weather?.weatherRequestState) {
                Weather.RequestState.Loading -> hideCityTemp()
                else -> hideProgressbar()
            }

            rootLayout.setOnClickListener {
                onCityClicked(city)
            }
            rootLayout.setOnLongClickListener {
                onCityLongClicked(city)
                true
            }
        }

        private fun hideProgressbar() {
            //Hide ProgressBar and update the Temp field
            cityTemp.isVisible = true
            loadingProgressBar.isVisible = false
        }

        private fun hideCityTemp() {
            //Hide the Temp Field and show the ProgressBar
            cityTemp.isVisible = false
            loadingProgressBar.isVisible = true
        }
    }
}
