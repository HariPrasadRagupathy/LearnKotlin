package com.hp.learnkotlin.ui.depen.hilt

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HiltViewModel @Inject constructor(private val getData : HiltDemo) : ViewModel()
{
    fun getViewModelData() : String{
        return getData.printData()
    }

}