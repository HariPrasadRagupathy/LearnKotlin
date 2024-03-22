package com.hp.learnkotlin.ui.compose

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.hp.learnkotlin.ui.DemoInterfaceListener
import com.hp.learnkotlin.ui.depen.hilt.HiltDemoUI
import com.hp.learnkotlin.ui.depen.koin.KoinDemoUI

@Composable
fun Int.pxToDp(): Dp {
    return with(LocalDensity.current) {
        this@pxToDp.dp / density
    }
}

@Composable
fun PaddingValues.addPadding(
    start: Dp = 0.dp,
    top: Dp = 0.dp,
    end: Dp = 0.dp,
    bottom: Dp = 0.dp
): PaddingValues {
    return PaddingValues(
        this.calculateStartPadding(LayoutDirection.Ltr) + start,
        this.calculateTopPadding() + top,
        this.calculateEndPadding(LayoutDirection.Ltr) + end,
        this.calculateBottomPadding() + bottom
    )
}

@Composable
fun PaddingValues.addPadding(
    all: Dp = 0.dp
): PaddingValues {
    return PaddingValues(
        this.calculateStartPadding(LayoutDirection.Ltr) + all,
        this.calculateTopPadding() + all,
        this.calculateEndPadding(LayoutDirection.Ltr) + all,
        this.calculateBottomPadding() + all
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutLearn() {
    
    val onClickListener = object :DemoInterfaceListener{
        override fun onClick(): String {
            Log.e("onClick","click listner worked")
            return ""
        }

    }

    Scaffold(bottomBar = {
        BottomAppBar(modifier = Modifier.height(100.pxToDp())) {
            Text(text = "fd")
        }
    }) {
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        val top25PercentHeight = remember { (screenHeight * 0.25f).coerceAtMost(screenHeight) }

        Column(modifier = Modifier.padding(it.addPadding(all = 5.dp))) {
            Row(modifier = Modifier
                .wrapContentHeight()
                .absolutePadding()
                .requiredHeight(top25PercentHeight)
                .fillMaxWidth()
                .background(Color.Transparent)
                .drawBehind {
                    val path = Path().apply {
                        addRoundRect(
                            roundRect = RoundRect(
                                left = 0f,
                                right = size.width,
                                top = 0f,
                                bottom = size.height,
                                radiusX = 25f,
                                radiusY = 25f
                            )
                        )
                    }
                    drawPath(path, color = Color.Red)
                }
                .padding(15.dp)

            ) {
                Text("hellow")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
            ) {
                Row(
                    modifier = Modifier
                        .height(100.pxToDp())
                        .fillMaxWidth()
                        .background(Color.Blue)
                ) {
                    Text("done")
                }
                DemoButton(clickListner = onClickListener){ a,b ->

                    return@DemoButton a+b
                }
                HiltDemoUI()
                Spacer(modifier = Modifier.height(10.dp))
                KoinDemoUI()

            }

        }
    }
}

@Composable
fun DemoButton(clickListner : DemoInterfaceListener, onClick : (Int, Int) -> Int){
    Button(
        onClick = {
            clickListner.onClick()
            val demo = onClick.invoke(5,4)
            Log.e("onClick","Demo $demo")
        }
    ){Text("Heelo")}
}