package com.hp.learnkotlin.ui.delegates.delegateclass

class DelegateRepo(
    private val customerRepo : CustomerRepo = CustomerRepoImpl(),
    private val vendorRepo: VendorRepo= VendorRepoImpl())
    : CustomerRepo by customerRepo, VendorRepo by vendorRepo
