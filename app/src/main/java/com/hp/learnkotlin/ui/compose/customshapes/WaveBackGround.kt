package com.hp.learnkotlin.ui.compose.customshapes


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp


class WavyShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, size.height * 0.3f)
            cubicTo(
                size.width * 0.25f, size.height * 0.4f,
                size.width * 0.75f, size.height * 0.2f,
                size.width.toFloat(), size.height * 0.3f
            )
            lineTo(size.width.toFloat(), size.height.toFloat())
            lineTo(0f, size.height.toFloat())
            close()
        }
        return Outline.Generic(path)
    }


}

fun Modifier.waveBackground(
    color: Color,
    waveHeight: Float
) = this.then(Modifier.drawBehind {
    val path = Path().apply {
        moveTo(0f, 0f)
        lineTo(0f, size.height * 0.5f)
        cubicTo(
            size.width * 0.25f, size.height,
            size.width * 0.75f, 0f,
            size.width, size.height * 0.5f
        )
        lineTo(size.width, 0f)
        close()
    }
    drawPath(path, color.copy(alpha = 0.2f))
})

class WavyBottomShape(private val waveHeight: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(0f, size.height * 0.5f)
            cubicTo(
                size.width * 0.25f, size.height,
                size.width * 0.75f, 0f,
                size.width, size.height * 0.5f
            )
            lineTo(size.width, 0f)
            close()
        }
        return Outline.Generic(path)
    }
}

@Composable
fun WaveBackground(
    modifier: Modifier,
    backGroundColor: Color = Color.White,
    waveHeight: Float = 100f,
    content: @Composable () -> Unit
) {
    Column() {
        Box(
            modifier = modifier
                .height(waveHeight.dp)
                .background(backGroundColor)
                //.waveBackground(backGroundColor,waveHeight)
                .fillMaxWidth()
        ) {
            content()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(waveHeight.dp)
               // .waveBackground(backGroundColor,waveHeight)
                .clip(WavyBottomShape(waveHeight))
                .background(backGroundColor)
        )
    }
}

/*@Composable
fun WaveBackground(
modifier: Modifier,
content: @Composable () -> Unit
) {
Box(
    modifier = modifier
        .fillMaxWidth()
        .clip(WavyBottomShape())
        .background(Color.Red)
    *//*
                .drawBehind {
                    val path = Path().apply {
                        moveTo(0f, size.height * 0.3f)
                        cubicTo(
                            size.width * 0.25f, size.height * 0.4f,
                            size.width * 0.75f, size.height * 0.2f,
                            size.width, size.height * 0.3f
                        )
                        lineTo(size.width, size.height)
                        lineTo(0f, size.height)
                        close()
                    }
                    drawPath(
                        path = path,
                        brush = SolidColor(Color(0xFFE0F7FA)),
                        style = androidx.compose.ui.graphics.drawscope.Fill,
                        //pathFillType = PathFillType.NonZero
                    )
                }*//*
    ) {
        Text("Hello1")
    }
}*/


@Preview
@Composable
fun PreviewWaveBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column() {
            WaveBackground(
                backGroundColor = Color.Blue,
                waveHeight = 50f,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Column {
                    Text(text = "Hello")
                    Text(text = "Hello")
                    Text(text = "Hello")
                    Text(text = "Hello")
                    Text(text = "Hello")
                    Text(text = "Hello")
                    Text(text = "Hello")
                    Text(text = "Hello")
                    Text(text = "Hello")
                    Text(text = "Hello")

                }

            }
            Text(text = "Heelo")
        }
    }

}