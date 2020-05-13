package com.example.teamplanningapp.data.source.repository

import android.app.Application
import com.example.teamplanningapp.Result
import com.example.teamplanningapp.data.source.UserDataSource
import com.example.teamplanningapp.data.source.remote.UserRemoteDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginDataRepository(
    private val remoteDataSource: UserDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LoginRepository {

    companion object {
        @Volatile
        private var INSTANCE: LoginDataRepository? = null

        fun getRepository(app: Application): LoginDataRepository {
            return INSTANCE ?: synchronized(this) {
                return@synchronized LoginDataRepository(
                    UserRemoteDataSource()
                ).also {
                    INSTANCE = it
                }
            }
        }
    }

    override suspend fun retrieveUser(email: String, password: String): Result<FirebaseUser?>{
        return withContext(ioDispatcher) {
            val user = remoteDataSource.getUser(email, password)
            if (user.result?.user != null) {
                return@withContext Result.Success(user.result?.user)
            } else {
                return@withContext Result.Error("Login Error")
            }
        }
    }
}