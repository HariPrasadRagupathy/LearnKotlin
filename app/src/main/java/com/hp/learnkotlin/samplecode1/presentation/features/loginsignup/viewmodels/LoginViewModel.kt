package com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.data.LoginEvent
import com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.data.LoginNavigation
import com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.data.LoginState
import com.hp.learnkotlin.samplecode1.repositories.SampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: SampleRepository
) : ViewModel() {

    private val _state = MutableLiveData(LoginState())
    val state : LiveData<LoginState> = _state

    private val _navigationEvent = MutableLiveData<LoginNavigation?>()
    val navigationEvent : LiveData<LoginNavigation?> = _navigationEvent

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UserNameChanged -> {
                _state.value = state.value?.copy(userName = event.userName)
            }

            is LoginEvent.PasswordChanged -> {
                _state.value = state.value?.copy(password = event.password)
            }

            LoginEvent.Submit -> {
                signIn()
            }
        }
    }

    private fun signIn() {
        _state.value = _state.value?.copy(isLoading = true)

        viewModelScope.launch {
            repository.signIn(_state.value!!.userName, _state.value!!.password).fold(
                onSuccess = {
                    _state.value = _state.value!!.copy(isLoading = false, errorMessage = null)
                    _navigationEvent.value = LoginNavigation.NavigateToDashboard
                },
                onFailure = {
                    _state.value = _state.value!!.copy(isLoading = false, errorMessage = it.message)
                }
            )
        }
    }
}