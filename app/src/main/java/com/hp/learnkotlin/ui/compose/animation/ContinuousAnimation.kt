package com.hp.learnkotlin.ui.compose.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ContinousAnimation() {

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotateAnimation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f ,
        animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing)), label = ""
    )

    val infiniteTransitionColor = rememberInfiniteTransition(label = "")

    val colorAnimation = infiniteTransitionColor.animateColor(
        initialValue = Color.White,
        targetValue = Color.Black ,
        animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing)), label = ""
    )

    Column(modifier = Modifier.fillMaxSize().background(colorAnimation.value), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("I will rotate", modifier = Modifier.rotate(rotateAnimation.value))
    }

}