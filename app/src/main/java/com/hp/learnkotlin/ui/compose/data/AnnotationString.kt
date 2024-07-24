package com.hp.learnkotlin.ui.compose.data

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalTextApi::class)
@Preview
@Composable
fun AnnotatedStringDemo() {
    val welcomeText = buildAnnotatedString {
        append("Welcome\n")
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                fontSize = 30.sp
            )
        ) {
            append("Hari Prasad")
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = Color.Red
            )
        ) {
            append(" !\n")
        }
        val str = "Let's open google!"

        pushStringAnnotation(tag = "https://www.google.com", annotation = str)
        withStyle(
            style = SpanStyle(textDecoration = TextDecoration.Underline, color = Color.Blue)
        ) {
            append(str)
        }
        pop()

    }
    val uriHandler = LocalUriHandler.current

    Column(modifier = Modifier.fillMaxSize()) {
        ClickableText(text = welcomeText) {
            Log.e("annot", "Clicked")
            welcomeText.getStringAnnotations(it, it).firstOrNull()
                ?.let { annotation ->
                    Log.e("annot", annotation.tag)
                    uriHandler.openUri(annotation.tag)
                }
        }
    }
}

@Preview
@Composable
fun AnnotatedStringParagraphStyle() {

    val paragraphStyle = ParagraphStyle(
        textAlign = TextAlign.Left,
        lineHeight = 20.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.LastLineBottom
        ),
        textIndent = TextIndent(firstLine = 24.sp, restLine = 0.sp),
        lineBreak = LineBreak.Paragraph,
        hyphens = Hyphens.Auto,
        textMotion = TextMotion.Animated
    )

    val styledParagraph = buildAnnotatedString {
        withStyle(paragraphStyle) {
            append("This is a paragraph with a line height of 24sp.\n")
            append("Notice how the extra space is distributed by default.\n")
            append("Line height styling is not applied here  in the area of the screen.")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Top check for triming the above paragraph")
        Text(text = styledParagraph)
        Text(text = "Bottom check for triming the above paragraph")
    }
}

@Preview
@Composable
fun AnnotatedStringSpanStyle() {
    val styledSpan = SpanStyle(
        color = Color.Red,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    val styledSuperScript = styledSpan.copy(
        baselineShift = BaselineShift(1f),
        fontSize = 12.sp
    )

    val styledTexedGeometery = styledSpan.copy(
        textGeometricTransform = TextGeometricTransform(skewX = -0.5f)
    )

    val shadowStyle = styledSpan.copy(shadow = Shadow(color = Color.Gray, blurRadius = 10f, offset = Offset(2f, 2f)))


    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotateAnimation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f ,
        animationSpec = infiniteRepeatable(tween(1250, easing = LinearEasing), repeatMode = RepeatMode.Restart), label = ""
    )
    val colorSet1 = listOf(Color.Red, Color.Cyan, Color.Green)
    val colorSet2 = listOf(Color.Green,Color.Red, Color.Cyan)
    val colorSet3 = listOf(Color.Cyan, Color.Green,Color.Red)
    val colorList = listOf(colorSet1, colorSet2, colorSet3)
    val colorFullStyle = styledSpan.copy(brush = Brush.horizontalGradient(colors = colorList[rotateAnimation.value.toInt()]), fontSize = 18.sp)


    val annotatedString = buildAnnotatedString {
        withStyle(style = styledSpan) {
            append("Hello")
        }
        withStyle(style = styledSuperScript) {
            append("rs")
        }
        withStyle(style = styledTexedGeometery) {
            append("Captain")
        }
        append("\n\n")
        withStyle(style = shadowStyle) {
            append("Shadow")
        }
        append("\n\n")
        withStyle(style = colorFullStyle) {
            append("New")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Top check for triming the above Line")
        Text(text = annotatedString)
        ColorChangingText("New")
    }
    /*   withStyle(style = SpanStyle(brush = Brush.horizontalGradient(listOf(Color.Red, Color.Cyan,
           Color.Green), startX = 10f), fontSize = 24.sp)){
           append("Color full text")
       }*/

}


@Composable
fun ColorChangingText(text : String, color: List<Color>?=null){
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val colors = listOf(Color.Red, Color.Cyan, Color.Green)

    val gradientBrush = Brush.horizontalGradient(
        colors = colors,
        startX = animatedOffset * 1000,
        endX = (animatedOffset * 1000) + 1000
    )

    Text(text = "New", style = TextStyle(brush = gradientBrush))
}