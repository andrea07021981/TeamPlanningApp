package com.example.teamplanningapp.data.source.remote

import androidx.lifecycle.MutableLiveData
import com.example.teamplanningapp.Result
import com.example.teamplanningapp.data.source.UserDataSource
import com.example.teamplanningapp.data.source.remote.service.FirebaseClient
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

//TODO remote login with firebase
class UserRemoteDataSource(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserDataSource {

    /**
     * A suspended coroutine waits until we receive a response from Firebase and then we resume it
     */
    override suspend fun getUser(email: String, password: String): Result<FirebaseUser> = suspendCoroutine{ cont ->
        FirebaseClient.firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isComplete && it.result != null) {
                    val user = it.result?.user
                    cont.resume(Result.Success(user!!))
                } else {
                    cont.resume(Result.Error("Login error"))
                }
            }
            .addOnCanceledListener {
                cont.resume(Result.Error("Login cancelled"))
            }
            .addOnFailureListener {
                cont.resume(Result.ExError(it))
            }
    }

}