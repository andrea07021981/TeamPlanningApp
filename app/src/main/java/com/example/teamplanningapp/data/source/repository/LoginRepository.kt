package com.example.teamplanningapp.data.source.repository

import androidx.lifecycle.LiveData
import com.example.teamplanningapp.Result
import com.google.firebase.auth.FirebaseUser

interface LoginRepository {
    val TAG: String
        get() = LoginRepository::class.java.simpleName

    suspend fun retrieveUser(email: String, password: String): Result<FirebaseUser>
}