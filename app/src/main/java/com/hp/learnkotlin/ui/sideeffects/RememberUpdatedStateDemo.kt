package com.hp.learnkotlin.ui.sideeffects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RememberUpdatedStateExample() {
    var buttonColour by remember {
        mutableStateOf("Unknown")
    }
    Column {
        Button(
            onClick = {
                buttonColour = "Red"
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            Text("Red Button")
        }
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                buttonColour = "Black"
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            Text("Black Button")
        }
        SideEffect {
            println("Button SideEffect")
        }
        TimerStart(buttonColour)
        TimerWithRememberUpdatedState(buttonColour = buttonColour)
    }
}

@Composable
fun TimerStart(
    buttonColour: String
) {
    val timerDuration = 5000L
    println("Composing timer with colour : $buttonColour")
    LaunchedEffect(key1 = Unit, block = {
        startTimer(timerDuration) {
            println("Timer ended")
            println("Last pressed button color was $buttonColour")
        }
    })
}

@Composable
fun TimerWithRememberUpdatedState(
    buttonColour: String
) {
    val timerDuration = 5000L
    println("Composing timer with colour : $buttonColour")
    val buttonColorUpdated by rememberUpdatedState(newValue = buttonColour)
    SideEffect {
        println("Timer SideEffect")
    }
    LaunchedEffect(key1 = Unit, block = {

        startTimer(timerDuration) {
            println("Timer ended")
            println("[1] Last pressed button color is $buttonColour")
            println("[2] Last pressed button color is $buttonColorUpdated")
        }
    })
}

suspend fun startTimer(time: Long, onTimerEnd: () -> Unit) {
    delay(timeMillis = time)
    onTimerEnd()
}