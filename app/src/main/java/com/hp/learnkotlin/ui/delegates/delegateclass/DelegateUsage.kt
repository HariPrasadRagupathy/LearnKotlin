package com.hp.learnkotlin.ui.delegates.delegateclass


fun main(){
    println("hello kotlin")
}

class DelegateUsage {
    private val repository = DelegateRepo()

    fun accessRepo(){
        repository.getCustomer()
        repository.createCustomer()
        repository.getVendor()
        repository.createVendor()
    }
}
