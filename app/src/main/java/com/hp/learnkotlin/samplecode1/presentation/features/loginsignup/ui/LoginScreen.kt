package com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hp.learnkotlin.samplecode1.presentation.common.nav.DASHBOARDSCREEN
import com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.data.DashboardState
//import com.hp.learnkotlin.BuildConfig
import com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.data.LoginEvent
import com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.data.LoginNavigation
import com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.viewmodels.LoginViewModel


val verticalSpace = 10.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleLoginScreen1(viewModel: LoginViewModel = hiltViewModel(), navController: NavController) {

    // region Navigation
    val navigationEvent by viewModel.navigationEvent.observeAsState()
    LaunchedEffect(navigationEvent) {
        when (navigationEvent) {
            LoginNavigation.NavigateToDashboard -> navController.navigate(DashboardState(defaultData = "Hari"))
            LoginNavigation.NavigateToForgotPassword -> {}
            LoginNavigation.NavigateToSignup -> {}
            null -> {}
        }
    }
    // endregion Navigation

    //region UI
    val state by viewModel.state.observeAsState(initial = viewModel.state.value)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Text("Hello World ${BuildConfig.BASE_URL}")

        Spacer(modifier = Modifier.height(verticalSpace))

        TextField(value = state!!.userName, onValueChange = {
            viewModel.onEvent(LoginEvent.UserNameChanged(it))
        })

        Spacer(modifier = Modifier.height(verticalSpace))

        TextField(value = state!!.password, onValueChange = {
            viewModel.onEvent(LoginEvent.PasswordChanged(it))
        })

        Spacer(modifier = Modifier.height(verticalSpace))

        Button(onClick = { viewModel.onEvent(LoginEvent.Submit) }, enabled = !state!!.isLoading) {
            Text(text = "Login")
        }

        AnimatedVisibility(visible = state!!.errorMessage != null) {
            Text(text = state!!.errorMessage.toString())
        }
    }
    //endregion UI

}


