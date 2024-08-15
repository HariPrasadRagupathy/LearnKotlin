package com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.viewmodels.DashboardViewModel

@Composable
fun DashBoardScreen1(
    viewModel: DashboardViewModel = hiltViewModel(),
    navController: NavController
){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        viewModel.state.value?.defaultData?.let { Text(text = it) }
    }

}