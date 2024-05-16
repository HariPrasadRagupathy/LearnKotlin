package com.hp.learnkotlin.ui.components.error

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hp.learnkotlin.ui.api.retrofit.RetroApiService
import com.hp.learnkotlin.ui.api.retrofit.request.SignInRequest
import com.hp.learnkotlin.ui.api.retrofit.response.SignInResponse
import com.hp.learnkotlin.ui.api.retrofit.toResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ErrorViewModal @Inject constructor(val retroApiService: RetroApiService) : ViewModel() {

  private var _errorData = MutableLiveData<String>()
   val errorData  : LiveData<String> get() = _errorData

    fun checkError(){
       viewModelScope.launch {
           val data = retroApiService.checkError().toResult()
           data.onSuccess {
               _errorData.value= it.token
           }.onFailure {
               _errorData.value= "error"
           }

       }
    }

}