package com.hp.learnkotlin.ui.observable

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun DemoObservable(){
    val vm : ObservableViewModal = viewModel()

    val scope = rememberCoroutineScope()

    val liveD by vm.liveData.observeAsState(initial = "data")

    var stateFlowData by remember {
        mutableStateOf("")
    }
    var flowData by remember {
        mutableStateOf("")
    }
    var sharedFlow by remember {
        mutableStateOf("one")
    }


    LaunchedEffect(key1 = Unit, block = {
        scope.launch {
            vm.stateFlow.collectLatest {
                stateFlowData = it
            }
            vm.triggerFlow().collectLatest {
                flowData = it
            }
            vm.sharedFlow.collectLatest {
                Log.e("shared","received")
                sharedFlow = it
            }
        }

    })

   // val liveInfo by vm.liveData.
    Column {
        Text(liveD)
        Text(stateFlowData)
        Text(text = flowData)
        Text(text = sharedFlow)
        Button(onClick = { vm.updateData() }) {
            Text("Update")
        }
        Button(onClick = {
            scope.launch {
                vm.triggerFlow().collectLatest {
                    flowData = it
                }
            }
        }){
            Text("Flow")
        }
    }

}