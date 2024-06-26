package com.hp.learnkotlin.ui.compose.animation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ContentSizeAnimation() {
    var expand by remember {
        mutableStateOf(false)
    }
    Column {
        Text(
            text = "Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content Hello Content",
            // Stiffness is kind of pulling the content down and damping is for spring effect
            modifier = Modifier.animateContentSize(
                animationSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessHigh)
            ),
            maxLines = if (expand) 10 else 2
        )
        Button(onClick = { expand = !expand }) {
            Text("Expand")
        }
    }

}