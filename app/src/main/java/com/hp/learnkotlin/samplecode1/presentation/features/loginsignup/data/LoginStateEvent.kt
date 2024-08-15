package com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.data

data class LoginState(
    val userName : String = "",
    val password : String = "",
    val isLoading : Boolean = false,
    val errorMessage : String? = null
)

sealed class LoginEvent{
    data class UserNameChanged(val userName : String) : LoginEvent()
    data class PasswordChanged(val password : String) : LoginEvent()
    object Submit : LoginEvent()
}

sealed class LoginNavigationEvent{
    object NavigateToDashboard : LoginNavigationEvent()
}
