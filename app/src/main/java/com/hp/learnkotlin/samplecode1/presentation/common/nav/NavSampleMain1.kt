package com.hp.learnkotlin.samplecode1.presentation.common.nav

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.ui.DashBoardScreen1
import com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.ui.SampleLoginScreen1
import kotlinx.serialization.Serializable


@Serializable object LoginSignupFeature

@Serializable object DashboardFeature


@Serializable object SAMPLELOGINSCREEN

@Serializable data class DASHBOARDSCREEN(val data : String)


@Composable
fun NavSampleMain1() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = LoginSignupFeature) {
        
        navigation<LoginSignupFeature>(startDestination = SAMPLELOGINSCREEN) {
            composable<SAMPLELOGINSCREEN> {
                SampleLoginScreen1(navController = navController)
            }
        }
        navigation<DashboardFeature>(startDestination = DASHBOARDSCREEN){
            composable<DASHBOARDSCREEN> {
                val args = it.toRoute<DASHBOARDSCREEN>()
                DashBoardScreen1(navController = navController, data = args)
            }
        }
    }


    /*NavHost(navController = navController, startDestination = LOGIN_SIGNUP_FEATURE) {
        navigation(route = LOGIN_SIGNUP_FEATURE, startDestination = SAMPLE_LOGIN_SCREEN) {
            composable(route = SAMPLE_LOGIN_SCREEN) {
                SampleLoginScreen1(navController = navController)
            }
            composable(route = SAMPLE_SIGNUP_SCREEN) {
                Text("Sample Signup Screen")
            }
            composable(route = SAMPLE_FORGOT_PASSWORD_SCREEN) {
                Text("Sample Forgot Password Screen")
            }
        }
        navigation(route = DASHBOARD_FEATURE, startDestination = DASHBOARD_SCREEN) {
            composable(route = DASHBOARD_SCREEN) {
                DashBoardScreen1(navController = navController)
            }
        }
    }*/
}