package com.hp.learnkotlin.ui.api.retrofit.impl.retro

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RetroUI(retroHiltViewModel: RetroHiltViewModel = hiltViewModel()){
    //val token by remember { mutableStateOf("") }
    val token by retroHiltViewModel.signLiveData.observeAsState(initial = "Token")
    Column {
        Text(text = token)
        Button(onClick = { retroHiltViewModel.userSignIn() }) {
            Text("signIn")
        }
    }
}