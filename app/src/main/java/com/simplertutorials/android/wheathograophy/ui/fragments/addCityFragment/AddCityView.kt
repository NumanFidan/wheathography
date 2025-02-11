package com.simplertutorials.android.wheathograophy.ui.fragments.addCityFragment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddCityView(
    onAddCityTapped: (String) -> Unit,
    onCancelTapped: () -> Unit
) {
    //refactor city_add_fragment.xml into comise code
    val textFieldState = rememberTextFieldState()
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(Modifier.align(Alignment.Center)) {
            BasicTextField(
                state = textFieldState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                lineLimits = TextFieldLineLimits.SingleLine,
                decorator = { innerTextField ->
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        innerTextField()
                    }
                }
            )
            if (textFieldState.text.isEmpty()) {
                Text(
                    "Type to add city",
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { onAddCityTapped(textFieldState.text.toString()) },
                modifier = Modifier.weight(1f)
            ) {
                Text("Add City")
            }
            Button(onClick = onCancelTapped, modifier = Modifier.weight(1f)) {
                Text("Cancel")
            }
        }
    }
}