package com.hp.learnkotlin.ui.observable

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ObservableViewModal : ViewModel() {

    private var _liveData = MutableLiveData<String>()
    val liveData : LiveData<String> get() = _liveData

    private val _stateFlow = MutableStateFlow<String>("")
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedStateFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedStateFlow.asSharedFlow()

    fun updateData(){
        _liveData.value = "updated value"

        viewModelScope.launch {
            _stateFlow.value = "Loading"
            delay(2000)
            _stateFlow.value = "StateFlow"
            Log.e("shared","sent")
           _sharedStateFlow.emit("hello")
            _sharedStateFlow.tryEmit("dfdfdf")
            Log.e("shared","sent1")
        }

        triggerFlow()
    }

    fun triggerFlow(): Flow<String> {
        return flow {
          repeat(10){
              emit(it.toString())
              delay(1000)
          }
        }
    }
}