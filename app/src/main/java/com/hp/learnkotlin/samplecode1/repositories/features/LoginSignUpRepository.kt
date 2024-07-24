package com.hp.learnkotlin.samplecode1.repositories.features

import kotlinx.coroutines.delay

typealias isSignedIn = Boolean

interface LoginSignUpRepository {
    suspend fun signIn(username : String, password : String) : Result<isSignedIn>
}

class LoginSignUpRepositoryImpl : LoginSignUpRepository {
    override suspend fun signIn(username: String, password: String): Result<isSignedIn> {
       return try {
           if(checkCredentials(username,password)){
               Result.success(true)
           }
           else{
               Result.failure(error("Invalid Credentials"))
           }
       }catch (e : Exception){
           Result.failure(e)
       }

    }

    private suspend fun checkCredentials(username: String, password: String): isSignedIn {
        delay(2000)
        return username.isNotEmpty() && password.isNotEmpty()
    }
}