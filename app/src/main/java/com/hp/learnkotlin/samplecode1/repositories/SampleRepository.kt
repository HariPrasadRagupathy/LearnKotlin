package com.hp.learnkotlin.samplecode1.repositories

import com.hp.learnkotlin.samplecode1.repositories.features.LoginSignUpRepository
import com.hp.learnkotlin.samplecode1.repositories.features.LoginSignUpRepositoryImpl

class SampleRepository(private val signUpRepository: LoginSignUpRepository = LoginSignUpRepositoryImpl()) :
    LoginSignUpRepository by signUpRepository