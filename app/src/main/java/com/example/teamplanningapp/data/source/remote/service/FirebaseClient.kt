package com.example.teamplanningapp.data.source.remote.service

import com.google.firebase.auth.FirebaseAuth

class FirebaseClient {

    companion object {

        val firebaseAuth: FirebaseAuth by lazy {
            FirebaseAuth.getInstance()
        }
    }
}