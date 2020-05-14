package com.example.teamplanningapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.teamplanningapp.Result
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface UserDataSource {

    suspend fun getUser(email: String, password: String): Result<FirebaseUser>

    //suspend fun saveUser(user: UserDomain): Long
}