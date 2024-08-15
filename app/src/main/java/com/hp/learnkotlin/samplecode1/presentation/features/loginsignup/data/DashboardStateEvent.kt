package com.hp.learnkotlin.samplecode1.presentation.features.loginsignup.data

data class DashboardState(
    val defaultData : String = "Hello World",
)

sealed class DashboardEvent{

}