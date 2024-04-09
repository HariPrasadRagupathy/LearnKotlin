package com.hp.learnkotlin

import android.app.Application
import com.hp.learnkotlin.ui.broadcasereceiver.AirPlaneModeReceiver
import com.hp.learnkotlin.ui.depen.koin.koinModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(koinModule)
        }
    }
}