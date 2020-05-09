package com.example.teamplanningapp.data.source.repository

import com.google.firebase.auth.FirebaseUser

interface LoginRepository {
    val TAG: String
        get() = LoginRepository::class.java.simpleName

    suspend fun retrieveUser(user: FirebaseUser)
}