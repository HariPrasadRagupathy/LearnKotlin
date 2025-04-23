package com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.data.DashboardState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel(){


    private val _state = MutableLiveData(DashboardState())
    val state : LiveData<DashboardState> = _state
}