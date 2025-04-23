package com.hp.learnkotlin.ui.localcomposition

import android.provider.CalendarContract
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

internal data class LocalColorTheme(
    var primary: Color = Color.Red,
    var onPrimary: Color = Color.Blue,
    var textColor: Color = Color.White
) {

}


internal val LocalTheme = compositionLocalOf { LocalColorTheme() }


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Preview
@Composable
fun LocalComposition() {

    MaterialTheme.colorScheme.background

    val customTheme = mapOf(
        "jio1" to LocalColorTheme(
            primary = Color.Red,
            onPrimary = Color.Blue,
            textColor = Color.White
        ),
        "jio2" to LocalColorTheme(
            primary = Color.Blue,
            onPrimary = Color.Magenta,
            textColor = Color.Yellow
        )
    )

    var selectedChipIndex by remember { mutableStateOf(customTheme.keys.first()) }
    var currentTheme by remember { mutableStateOf(customTheme[selectedChipIndex]) }


// Providing new values to the CompositionLocal
    CompositionLocalProvider(LocalTheme provides currentTheme!!) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp),
                color = LocalTheme.current.primary ?: Color.Gray,
                shape = RoundedCornerShape(5.dp),
                tonalElevation = 10.dp
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ContentComposable()
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            FlowRow {

                customTheme.keys.forEach { key ->
                    FilterChip(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        selected = selectedChipIndex == key,
                        onClick = {
                            selectedChipIndex = key
                            currentTheme = customTheme[key] ?: LocalColorTheme()
                        },
                        label = { Text(text = key) })
                }


            }
        }

    }
}

@Composable
fun ContentComposable() {
    Button(
        onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
            containerColor = LocalTheme.current.onPrimary ?: Color.Gray
        )
    ) {
        TextComposable()

    }
}

@Composable
fun TextComposable() {
    Text(
        "Hello", modifier = Modifier
            .padding(), color = LocalTheme.current.textColor
    )
}