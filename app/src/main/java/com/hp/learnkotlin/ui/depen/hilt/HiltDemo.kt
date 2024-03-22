package com.hp.learnkotlin.ui.depen.hilt

import android.util.Log

interface HiltDemo {
    fun printData() : String
}

class HiltDemoImpl() : HiltDemo {
    override fun printData() : String {
        Log.e("hilt","hiltDemo")
        return "hiltDemo"
    }

}