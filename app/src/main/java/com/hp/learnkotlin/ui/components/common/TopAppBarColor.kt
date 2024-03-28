package com.hp.learnkotlin.ui.components.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

object CommonSettings {
    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun getTopAppBarColors(): TopAppBarColors {
        return TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        )
    }
}