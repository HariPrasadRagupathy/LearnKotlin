package com.hp.learnkotlin.ui.broadcasereceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class AirPlaneModeReceiver : BroadcastReceiver() {

   //val vm : BroadCastViewModel = BroadCastViewModel()

    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED){
            val isTurnedOn = Settings.Global.AIRPLANE_MODE_ON
            Log.e("AirPlan",isTurnedOn.toString())
        }
        if(intent?.action == "TEST_ACTION"){
            Log.e("AirPlan","TestACTION")
            //vm.setState("changed")
            _liveData.value = "Changed"

        }
    }

    companion object {
        private var _liveData = MutableLiveData<String>()
        val liveData : LiveData<String> get() = _liveData
    }

}