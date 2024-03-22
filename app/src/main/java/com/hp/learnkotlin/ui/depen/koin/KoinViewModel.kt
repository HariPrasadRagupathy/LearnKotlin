package com.hp.learnkotlin.ui.depen.koin

import androidx.lifecycle.ViewModel
import com.hp.learnkotlin.ui.depen.hilt.HiltDemo
import dagger.hilt.android.lifecycle.HiltViewModel


class KoinViewModel(private val repo : KoinDemo) : ViewModel() {
    fun koinVMDemo() : String {
        return repo.printKoin()
    }
}