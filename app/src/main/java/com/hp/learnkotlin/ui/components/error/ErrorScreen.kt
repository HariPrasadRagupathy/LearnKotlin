package com.hp.learnkotlin.ui.components.error

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ErrorCheckScreen(viewModal: ErrorViewModal = hiltViewModel()) {
    val errorData by viewModal.errorData.observeAsState(initial = "Data")
    //val data = errorData.fold(onSuccess = { data -> "Success $data" }, onFailure = { "Error" })
    Column {
        Text(errorData ?: "Default")
        Button(onClick = { viewModal.checkError() }) {
            Text("Click")
        }
    }
}