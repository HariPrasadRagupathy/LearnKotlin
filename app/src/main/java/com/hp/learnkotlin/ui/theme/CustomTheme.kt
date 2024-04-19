package com.hp.learnkotlin.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import javax.annotation.concurrent.Immutable

@Immutable
data class CustomColors(
    val primary: Color,
    val onPrimary: Color,
    val backGroundGradientColor: List<Color>
)

val LocalCustomColors = staticCompositionLocalOf {
    CustomColors(
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        backGroundGradientColor = listOf(Color.Unspecified, Color.Unspecified)
    )
}


@Immutable
data class CustomSpacing(
    val spaceXs: Dp= Dp.Unspecified,
    val spaceS: Dp= Dp.Unspecified,
    val spaceM: Dp = Dp.Unspecified,
    val spaceL: Dp = Dp.Unspecified,
    val spaceXL: Dp = Dp.Unspecified
)

val LocalCustomSpacing = staticCompositionLocalOf {
    CustomSpacing(
        spaceXs = Dp.Unspecified,
        spaceS = Dp.Unspecified,
        spaceM = Dp.Unspecified,
    )
}


object CustomTheme {
    val colors: CustomColors
        @Composable
        get() = LocalCustomColors.current

    val spacing: CustomSpacing
        @Composable
        get() = LocalCustomSpacing.current
}