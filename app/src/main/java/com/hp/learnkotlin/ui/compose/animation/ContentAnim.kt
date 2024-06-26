package com.hp.learnkotlin.ui.compose.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun ContentAnimationDemo() {

    var progress by remember { mutableFloatStateOf(0f) }

    var progressAnim = animateFloatAsState(
        targetValue = progress, label = ""
    )

    Column(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 10.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.requiredHeightIn(min = 50.dp, max = 100.dp)) {
            LinearProgressIndicator(
                progress = progressAnim.value,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            AnimatedContent(targetState = progress, label = "", transitionSpec = {
                slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
            }) { current ->
                when (current) {
                    0.0f -> BlueScreen()
                    0.25f -> RedScreen()
                    0.5f -> GreenScreen()
                    1.0f -> MagentaScreen()
                }
            }

        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ) {
            Button(onClick = { progress -= 0.25f }) {
                Text("Previous")
            }
            Button(onClick = { progress += 0.25f }) {
                Text("Next")
            }
        }

    }
}

@Composable
fun BlueScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {

    }
}

@Composable
fun RedScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {

    }
}

@Composable
fun GreenScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {

    }
}

@Composable
fun MagentaScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {

    }
}