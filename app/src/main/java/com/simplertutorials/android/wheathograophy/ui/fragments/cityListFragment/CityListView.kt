package com.simplertutorials.android.wheathograophy.ui.fragments.cityListFragment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.simplertutorials.android.wheathograophy.R
import com.simplertutorials.android.wheathograophy.domain.City
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityListView(
    cityListFlow: StateFlow<List<City>>,
    isRefreshingStateFlow : StateFlow<Boolean>,
    onCityTapped: (City) -> Unit,
    onAddCityTapped: () -> Unit,
    onRefresh: () -> Unit,
) {
    val cityList by cityListFlow.collectAsStateWithLifecycle(emptyList<City>())
    val isRefreshing by isRefreshingStateFlow.collectAsStateWithLifecycle(false)

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn {
            items(cityList) { city ->
                Text(
                    text = city.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clickable { onCityTapped(city) }
                )
            }

            item {
                Button(
                    onClick = onAddCityTapped,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(stringResource(R.string.add_city))
                }
            }

        }
    }
}

@Preview
@Composable
private fun PreviewCityListView() {
    CityListView(
        cityListFlow = MutableStateFlow(
            listOf(
                City("City 1"),
                City("City 2"),
                City("City 3")
            )
        ),
        isRefreshingStateFlow = MutableStateFlow(false),
        onCityTapped = {},
        onAddCityTapped = {},
        onRefresh = {}
    )
}