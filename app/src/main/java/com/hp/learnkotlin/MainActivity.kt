package com.hp.learnkotlin

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hp.learnkotlin.ui.broadcasereceiver.AirPlaneModeReceiver
import com.hp.learnkotlin.ui.components.DemoScreen
import com.hp.learnkotlin.ui.compose.LayoutLearn
import com.hp.learnkotlin.ui.nav.NavMain
import com.hp.learnkotlin.ui.observable.DemoObservable
import com.hp.learnkotlin.ui.theme.LearnKotlinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val airPlaneModeReceiver = AirPlaneModeReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerReceiver(
            airPlaneModeReceiver,
            //IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED),
            IntentFilter("TEST_ACTION")
        )

        setContent {

            LearnKotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavMain()
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

data class TryScract(var hello : Int)