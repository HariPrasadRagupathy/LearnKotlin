package com.hp.learnkotlin.samplecode1.presentation.common.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.ui.DashBoardScreen1
import com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.ui.SampleLoginScreen1
import kotlinx.serialization.Serializable


@Serializable
object LoginSignupFeature

@Serializable
object DashboardFeature


@Serializable
object SampleLoginScreen1

@Serializable
data class DashboardScreen(val defaultData : String = "Default Data")


@Composable
fun NavSampleMain1() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = LoginSignupFeature) {
        
        navigation<LoginSignupFeature>(startDestination = SampleLoginScreen1) {
            composable<SampleLoginScreen1> {
                SampleLoginScreen1(navController = navController)
            }
        }
        navigation<DashboardFeature>(startDestination = DashboardScreen){
            composable<DashboardScreen> {
               // val args = it.toRoute<DASHBOARDSCREEN>()
                DashBoardScreen1(navController = navController)
            }
        }
    }
}