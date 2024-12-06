package com.example.firebaseapplication

import android.app.Application
import com.google.firebase.FirebaseApp

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase when the application starts
        FirebaseApp.initializeApp(this)
    }
}
