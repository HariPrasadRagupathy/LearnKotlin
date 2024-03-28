package com.hp.learnkotlin.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.hp.learnkotlin.ui.components.DemoScreen
import com.hp.learnkotlin.ui.components.LoginScreen
import com.hp.learnkotlin.ui.components.SettingsScreen

@Composable
fun NavMain() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = if (true) ScreenGroup.DashBoardGroup.route else ScreenGroup.AuthGroup.route
    ) {
        navigation(
            startDestination = ScreenGroup.AuthGroup.startDestination.route,
            route = ScreenGroup.AuthGroup.route
        ) {
            composable(Screens.LoginScreen.route) {
                LoginScreen(navController = navController)
            }
        }
        navigation(
            startDestination = ScreenGroup.DashBoardGroup.startDestination.route,
            route = ScreenGroup.DashBoardGroup.route
        ) {
            navigation("nested_auth_one", "nested_auth") {
                composable("nested_auth_one") {
                    DemoScreen(navController = navController)
                }
            }
            composable(Screens.HomeScreen.route) {
                DemoScreen(navController = navController)
            }
            composable(Screens.SettingScreen.route) {
                SettingsScreen(navController = navController)
            }
        }
    }

}

sealed class ScreenGroup(val route: String, val startDestination: Screens) {
    object AuthGroup : ScreenGroup(route = "auth_group", startDestination = Screens.LoginScreen)
    object DashBoardGroup : ScreenGroup(route = "dash_group", startDestination = Screens.HomeScreen)
}

sealed class Screens(val route: String, val title: String = "") {
    object HomeScreen : Screens(route = "home_screen", title = "DashBoard")
    object CallScreen : Screens(route = "call_screen", title = "call")
    object NotificationsScreen : Screens(route = "notification_screen", title = "Notifications")
    object SettingScreen : Screens(route = "settings_screen")
    object LoginScreen : Screens(route = "login_screen")
}