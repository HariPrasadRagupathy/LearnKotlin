package com.hp.learnkotlin.ui.depen.hilt

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HiltDemoUI(vm : HiltViewModel = hiltViewModel()){
   Text(vm.getViewModelData())
}