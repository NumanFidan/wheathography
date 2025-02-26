package com.simplertutorials.android.wheathograophy.ui.fragments.weatherInfoFragment

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherInfoView(
    cityName: String,
    temperature: String?,
    humidity: String?,
    weatherDescription: String?
) {
    Box(
        Modifier.fillMaxSize()
    ) {
        Column(
            Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(cityName, fontSize = 36.sp)
            temperature?.let { Text(it, fontSize = 24.sp) }
            humidity?.let { Text(it, fontSize = 24.sp) }
            weatherDescription?.let { Text(it, fontSize = 24.sp) }
        }
    }
}