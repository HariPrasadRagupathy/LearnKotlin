package com.hp.learnkotlin.ui.depen.koin

interface KoinDemo {
    fun printKoin() : String
}

class KoinDemoImpl() : KoinDemo {
    override fun printKoin(): String {
        return "KoinDemo"
    }
}