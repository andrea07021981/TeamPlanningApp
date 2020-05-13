package com.example.teamplanningapp.data.source.remote

import com.example.teamplanningapp.Result
import com.example.teamplanningapp.data.source.UserDataSource
import com.example.teamplanningapp.data.source.remote.service.FirebaseClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.coroutines.suspendCoroutine

//TODO remote login with firebase
class UserRemoteDataSource(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserDataSource {

    override suspend fun getUser(email: String, password: String): Task<AuthResult> = withContext(ioDispatcher){
        return@withContext FirebaseClient.firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isComplete) {
                    it
                }
            }
            .addOnCompleteListener { it }
    }

}