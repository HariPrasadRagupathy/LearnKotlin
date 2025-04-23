package com.hp.learnkotlin

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hp.learnkotlin.samplecode1.presentation.common.nav.NavSampleMain1
import com.hp.learnkotlin.ui.broadcasereceiver.AirPlaneModeReceiver
import com.hp.learnkotlin.ui.components.DemoScreen
import com.hp.learnkotlin.ui.compose.LayoutLearn
import com.hp.learnkotlin.ui.localcomposition.LocalComposition
import com.hp.learnkotlin.ui.nav.NavMain
import com.hp.learnkotlin.ui.notifications.AppNotification
import com.hp.learnkotlin.ui.notifications.NotificationDemo
import com.hp.learnkotlin.ui.observable.DemoObservable
import com.hp.learnkotlin.ui.sideeffects.RememberUpdatedStateExample
import com.hp.learnkotlin.ui.theme.LearnKotlinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val airPlaneModeReceiver = AirPlaneModeReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppNotification.createNotificationChannels(this)

        registerReceiver(
            airPlaneModeReceiver,
            //IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED),
            IntentFilter("TEST_ACTION")
        )

        setContent {

            LearnKotlinTheme {
                // A surface container using the 'background' color from the theme

                val startTime = System.nanoTime()
                    Button(onClick = {}) { Text("Click Me") }
                val endTime = System.nanoTime()
                val durationInNanos = endTime - startTime
                val durationInMillis = durationInNanos / 1_000_000

                Log.e("time","PropertyTime $durationInMillis ms")

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //NavMain()
                    //NavSampleMain1()
                    //LocalComposition()


                    RememberUpdatedStateExample()


                    // NotificationDemo()
                    //DemoScreen()
                    //LayoutLearn()
                    /* Column {
                         Greeting("Android")
                         DemoObservable()
                     }*/

                }
            }
        }
    }

    override fun onDestroy() {
        unregisterReceiver(airPlaneModeReceiver)
        super.onDestroy()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearnKotlinTheme {
        Greeting("Android")
    }
}

data class TryScract(var hello: Int)