package com.hp.learnkotlin.ui.broadcasereceiver

import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BroadCastViewModel @Inject constructor() : ViewModel(){

    private var _liveData = MutableLiveData<String>()
    val liveData : LiveData<String> get() = AirPlaneModeReceiver.liveData

    fun setState(data : String){
        _liveData.value = data
    }
}