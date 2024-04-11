package com.hp.learnkotlin.ui.broadcasereceiver

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BroadCastUIReceiver(){

  val context = LocalContext.current

  //MaterialTheme.typography.

  val vm : BroadCastViewModel = hiltViewModel()

  val data by vm.liveData.observeAsState(initial = "loading")

  Text(data)
}