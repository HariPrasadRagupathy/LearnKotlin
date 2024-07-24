package com.hp.learnkotlin.samplecode1.presentation.common.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.ui.SampleLoginScreen1


const val LOGIN_SIGNUP_FEATURE = "LoginSignUpFeature"

const val SAMPLE_LOGIN_SCREEN = "Sample Login Screen"

@Composable
fun NavSampleMain1(){
    val navController = rememberNavController()
    NavHost(navController = navController , startDestination = LOGIN_SIGNUP_FEATURE){
        navigation(route = LOGIN_SIGNUP_FEATURE, startDestination = SAMPLE_LOGIN_SCREEN){
            composable(route = SAMPLE_LOGIN_SCREEN){
                SampleLoginScreen1()
            }
        }
    }
}