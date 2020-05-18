package com.simplertutorials.android.wheathograophy.ui.adapters

import android.annotation.SuppressLint
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
import com.simplertutorials.android.wheathograophy.ui.customListeners.OnCityClickListener
import kotlinx.android.synthetic.main.city_list_recyclerv_row.view.*


class CityListAdapter(private val cityListData: ArrayList<City>,
                      private val onCityClickListener: OnCityClickListener,
                      private val apiRepository: ApiRepository,
                      private val apiService: ApiService)
    : RecyclerView.Adapter<CityListAdapter.CityListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CityListHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.city_list_recyclerv_row, parent, false)
        return CityListHolder(view)
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

        holder.layout.setOnLongClickListener {
            onCityClickListener.onCityLongClicked(city)
            true
        }

        //Get weather Info from API
        //We need to do this here to create a partially loading effect with RecyclerView
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
        //Hide ProgressBar and update the Temp field
        holder.cityTemp.visibility = View.VISIBLE
        holder.loading.visibility = View.GONE
    }
    private fun hideCityTemp(holder: CityListHolder) {
        //Hide the Temp Field and show the ProgressBar
        holder.cityTemp.visibility = View.GONE
        holder.loading.visibility = View.VISIBLE
    }

    class CityListHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val loading = view.progressBar as ProgressBar
        val cityName = view.row_cityname as TextView
        val cityTemp = view.row_cityweather as TextView
        val layout = view
    }


}