package com.hp.learnkotlin.ui.components.error

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hp.learnkotlin.ui.api.retrofit.RetroApiService
import com.hp.learnkotlin.ui.api.retrofit.toResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ErrorViewModal @Inject constructor(val retroApiService: RetroApiService) : ViewModel() {

  private var _errorData = MutableLiveData<Result<String>>()
   val errorData  : LiveData<Result<String>> get() = _errorData

    fun checkError(){
       viewModelScope.launch {
           _errorData.value=retroApiService.checkError().toResult()
       }
    }

}