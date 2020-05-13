package com.example.teamplanningapp.data.source.local

import com.example.teamplanningapp.Result
import com.example.teamplanningapp.data.source.UserDataSource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//TODO user firebase offline here
class UserLocalDataSource internal constructor(
    //private val userDatabaseDao: UserDatabaseDao,
    //private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserDataSource {
    override suspend fun getUser(email: String, password: String): Task<AuthResult> {
        TODO("Not yet implemented")
    }
}