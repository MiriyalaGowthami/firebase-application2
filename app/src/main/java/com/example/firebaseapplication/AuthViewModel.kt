package com.example.firebaseapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class AuthViewModel(application: Application):AndroidViewModel(application) {
    private val authRepository=AuthRepository()

    fun registerUser(name:String,email:String,rollNo:String,password:String,onResult:(Boolean,String?)->Unit){
        authRepository.registerUser(name,email, rollNo, password, onResult)
    }

    fun loginUser(email:String,rollNo: String,onResult: (Boolean, String?) -> Unit){
        authRepository.loginUser(email, rollNo, onResult)
    }

    fun isLoggedIn()=authRepository.isLoggedIn()
}