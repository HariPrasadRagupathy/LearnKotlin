package com.hp.learnkotlin.ui.components

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hp.learnkotlin.ui.broadcasereceiver.BroadCastViewModel
import com.hp.learnkotlin.ui.components.common.CommonSettings
import com.hp.learnkotlin.ui.compose.addPadding
import com.hp.learnkotlin.ui.nav.Screens
import com.hp.learnkotlin.ui.pagger.presentation.UserScreen
import com.hp.learnkotlin.ui.theme.CustomTheme
import com.hp.learnkotlin.ui.theme.LearnKotlinTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoScreen(navController: NavController) {
    var screen by remember {
        mutableStateOf<Screens>(Screens.HomeScreen)
    }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = MaterialTheme.colorScheme.onPrimary) {
                Box(
                    modifier = Modifier
                        .requiredHeight(200.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text(text = "Title")
                }
                Spacer(modifier = Modifier.height(5.dp))
                NavigationDrawerItem(
                    shape = RectangleShape,
                    label = { Text("Check") },
                    selected = true,
                    onClick = {
                        scope.launch {
                            navController.navigate(Screens.SettingScreen.route)
                            drawerState.close()

                        }
                    })
            }
        }) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = screen.title) },
                    colors = CommonSettings.getTopAppBarColors(),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply { if (isClosed) open() else close() }
                            }
                        }) {
                            Icon(Icons.Default.Menu, "")
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate(Screens.SettingScreen.route) }) {
                            Icon(Icons.Default.Settings, "")
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Default.MoreVert, "")
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                    contentPadding = PaddingValues(horizontal = 5.dp),
                    actions = {
                        IconButton(onClick = { screen = Screens.HomeScreen }) {
                            Icon(Icons.Default.Home, contentDescription = "Home")
                        }
                        IconButton(onClick = { screen = Screens.CallScreen }) {
                            Icon(Icons.Default.Person, contentDescription = "call")
                        }
                        IconButton(onClick = { screen = Screens.NotificationsScreen }) {
                            Icon(Icons.Default.Notifications, contentDescription = "notification")
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            onClick = { navController.navigate(Screens.SettingScreen.route) }) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "")
                        }
                    })

                /* BottomAppBar(
                     containerColor = MaterialTheme.colorScheme.primaryContainer,
                     contentColor = MaterialTheme.colorScheme.primary

                 ) {
                     IconButton(onClick = { screen = Screens.HomeScreen }) {
                         Icon(Icons.Default.Home, contentDescription = "Home")
                     }
                     IconButton(onClick = { screen = Screens.CallScreen }) {
                         Icon(Icons.Default.Call, contentDescription = "call")
                     }
                     Spacer(modifier = Modifier.weight(1f))
                     FloatingActionButton(
                         containerColor = MaterialTheme.colorScheme.secondaryContainer,
                         onClick = { navController.navigate(Screens.SettingScreen.route) }) {
                         Icon(Icons.Default.Add, contentDescription = "")
                     }
                 }*/
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Add, contentDescription = "")
                }
            }
        ) { innerPadding ->
            Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors()) {

            }
            Surface {

            }
            Card {

            }
            Column(modifier = Modifier.padding(innerPadding.addPadding(5.dp))) {
                when (screen) {
                    Screens.HomeScreen -> {
                        DashBoardContent()
                    }

                    Screens.CallScreen -> {
                        //CallContent()
                        UserScreen()
                    }

                    Screens.NotificationsScreen -> {
                        NotificationsContent()
                    }

                    else -> {}
                }
            }
        }
    }
}


@Composable
fun DashBoardContent() {
    val vm: BroadCastViewModel = hiltViewModel()
    val data by vm.liveData.observeAsState(initial = "loading")
    val context = LocalContext.current

    val customColors = CustomTheme.colors

    Column {

        Text(text = data)
        Spacer(modifier = Modifier.height(CustomTheme.spacing.spaceM))
        Button(
            onClick = {
                context.sendBroadcast(Intent("TEST_ACTION"))
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = CustomTheme.colors.primary,
                contentColor = CustomTheme.colors.onPrimary
            )
        ) {
            Text("Trigger")
        }
        Spacer(modifier = Modifier.height(CustomTheme.spacing.spaceM))
        GradBox()
    }
}


@Composable
fun GradBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(
                Brush.horizontalGradient(CustomTheme.colors.backGroundGradientColor),
                shape = RoundedCornerShape(CustomTheme.spacing.spaceM)
            )
    ) {
        Text("Heello")
    }
}

@Preview
@Composable
fun PreviewGradBox() {
    LearnKotlinTheme {
        GradBox()
    }
}


@Composable
fun CallContent() {
    Text(text = "Call")
}

