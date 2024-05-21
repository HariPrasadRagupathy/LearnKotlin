package com.hp.learnkotlin.ui.compose.customshapes.prac

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp


class PracticeShape1() : Shape{
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width, size.height*0.50f)
            cubicTo(
                x1=size.width * 0.65f, y1=size.height*0.25f,
                x2=size.width * 0.35f, y2=size.height*0.75f,
                x3=0f, y3=size.height * 0.5f
            )
            close()
        }


        return Outline.Generic(path)
    }

}

@Preview
@Composable
fun DrawShapes1(){
  Box(modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight(0.5f)
      .clip(PracticeShape1())
      .background(Color(0xFF00CACE))) {

  }
}

@Composable
fun DrawShapes2() {
    Box(modifier = Modifier.size(100.dp)) {
        android.graphics.Canvas()
    }
}




@Preview
@Composable
fun PreviewBoard(){
    Column(modifier = Modifier.background(Color.White)) {
        DrawShapes1()
        Spacer(modifier = Modifier.height(10.dp))
        Text("Hello")
    }
}
