package com.hp.learnkotlin.ui.depen.koin


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {
    single<KoinDemo> { KoinDemoImpl() }
    viewModel { KoinViewModel(get()) }
}