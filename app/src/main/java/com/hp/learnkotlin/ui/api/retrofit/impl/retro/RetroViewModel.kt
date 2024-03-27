package com.hp.learnkotlin.ui.api.retrofit.impl.retro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hp.learnkotlin.ui.api.retrofit.RetroApiService
import com.hp.learnkotlin.ui.api.retrofit.request.SignInRequest
import com.hp.learnkotlin.ui.api.retrofit.response.SignInResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class RetroHiltViewModel @Inject constructor(private val hiltApiService: RetroApiService) :
    ViewModel() {


    private var _signLiveData = MutableLiveData<String>()
    val signLiveData: LiveData<String> get() = _signLiveData

    fun userSignIn() {
        viewModelScope.launch {
            try {
                val response: Response<SignInResponse> =  hiltApiService.signIn(SignInRequest("SuperAdmin", "123456"))
                if (response.isSuccessful) {
                    // Request successful, handle SignInResponse
                    val signInResponse: SignInResponse? = response.body()
                    _signLiveData.value = signInResponse?.token?:""
                    // Do something with signInResponse
                } else {
                    // Request failed, handle error
                    val errorBody = response.errorBody()?.string()
                    _signLiveData.value = ""
                    // Handle error body or response code
                }
            } catch (e: Exception) {
                // Exception occurred, handle it
                e.printStackTrace()
            }
        }
    }

}


