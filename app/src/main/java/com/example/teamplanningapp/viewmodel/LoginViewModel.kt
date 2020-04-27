package com.example.teamplanningapp.viewmodel

import android.app.Application
import androidx.lifecycle.*

class LoginViewModel(
    application: Application
) : ViewModel() {

    var emailValue = MutableLiveData<String>()
    var passwordValue = MutableLiveData<String>()
    var errorPassword = MutableLiveData<Boolean>()
    var errorEmail = MutableLiveData<Boolean>()

    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LoginViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}