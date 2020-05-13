package com.example.teamplanningapp

import android.app.Application
import com.google.firebase.FirebaseApp

class TeamApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this);
    }
}