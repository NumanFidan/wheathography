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
import com.simplertutorials.android.wheathograophy.data.api.ApiRepository
import com.simplertutorials.android.wheathograophy.domain.City
import kotlinx.android.synthetic.main.city_list_recyclerv_row.view.*
import com.simplertutorials.android.wheathograophy.subscribe

class CityListAdapter(
    context: Context,
    private val cityListData: ArrayList<City>,
    private val apiRepository: ApiRepository,
    private val onCityClicked: (City) -> Unit,
    private val onCityLongClicked: (City) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

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
            hideCityTemp()

            rootLayout.setOnClickListener {
                onCityClicked(city)
            }
            rootLayout.setOnLongClickListener {
                onCityLongClicked(city)
                true
            }

            //Get weather Info from API
            //We need to do this here to create a partially loading effect with RecyclerView
            apiRepository.getWeatherInfo(city)
                .subscribe(
                    onNext = { n ->
                        cityTemp.text =
                            String.format("%.2f", n.informationCube!!.temp - 273.15) + "°C"
                        hideProgressbar()
                    },
                    onError = { e ->
                        cityTemp.text = "!"
                        hideProgressbar()
                    })

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
