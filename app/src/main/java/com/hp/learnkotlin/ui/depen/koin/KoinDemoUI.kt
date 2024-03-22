package com.hp.learnkotlin.ui.depen.koin

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun KoinDemoUI(koinViewModel: KoinViewModel = koinViewModel()) {

    val koinValue = koinInject<KoinDemo>()
    Column {
        Text(koinViewModel.koinVMDemo())
        Text(koinValue.printKoin())
    }

}