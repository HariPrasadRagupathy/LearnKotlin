package com.hp.learnkotlin.ui.compose.data

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
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
        lineHeight = 50.sp,
        lineHeightStyle = LineHeightStyle(alignment = LineHeightStyle.Alignment.Center,trim = LineHeightStyle.Trim.LastLineBottom),
        textIndent = TextIndent(firstLine = 24.sp, restLine = 0.sp),

    )



    val styledParagraph = buildAnnotatedString {
        withStyle(paragraphStyle) {
            append("This is a paragraph with a line height of 24sp.\n")
            append("Notice how the extra space is distributed by default.\n")
            append("Line height styling is not applied here.")
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
fun AnnotatedStringSpanStyle(){
    val spanStyle = SpanStyle(

    )
}