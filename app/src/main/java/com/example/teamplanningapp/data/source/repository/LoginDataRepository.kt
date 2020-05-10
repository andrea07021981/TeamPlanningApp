package com.example.teamplanningapp.data.source.repository

import android.app.Application
import android.util.Log
import com.example.teamplanningapp.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginDataRepository : LoginRepository {

    private lateinit var auth: FirebaseAuth

    companion object {
        @Volatile
        private var INSTANCE: LoginDataRepository? = null

        fun getRepository(app: Application): LoginDataRepository {
            return INSTANCE ?: synchronized(this) {
                //TODO DB WHEN READY
                return@synchronized LoginDataRepository(
                ).also {
                    INSTANCE = it
                }
            }
        }
    }

    override suspend fun retrieveUser(user: FirebaseUser) {
        //TODO decide if here of create a remote data source class
        auth.signInWithEmailAndPassword("test", "Etas")
            .addOnCompleteListener { task ->
                if (task.isComplete) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
            }
            .addOnFailureListener {
                //Error, throw exception
            }
    }
}