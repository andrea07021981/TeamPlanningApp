package com.example.teamplanningapp.viewmodel

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.*
import com.example.teamplanningapp.constant.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(
    application: Application
) : ViewModel() {

    var emailValue = MutableLiveData<String>()
    var passwordValue = MutableLiveData<String>()
    var errorPassword = MutableLiveData<Boolean>()
    var errorEmail = MutableLiveData<Boolean>()

    private val _loginAuthenticationState = MutableLiveData<LoginAuthenticationStates>()
    val loginAuthenticationState: LiveData<LoginAuthenticationStates>
        get() = _loginAuthenticationState

    private val _navigateToSignUpFragment = MutableLiveData<Boolean>()
    val navigateToSignUpFragment: LiveData<Boolean>
        get() = _navigateToSignUpFragment

    private var viewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    //TODO ADD BUTTON ANIMATION STATE LOGIN AND REVEAL animation transition
    //https://gist.github.com/ferdy182/d9b3525aa65b5b4c468a

    init {
        emailValue.value = "a@a.com"
        passwordValue.value = "a"
    }

    fun onSignUpClick(){
        errorEmail.value = emailValue.value.isNullOrEmpty()
        errorPassword.value = passwordValue.value.isNullOrEmpty()
        if (errorEmail.value == false && errorPassword.value == false) {
            doLogin()
        }
    }

    private fun doLogin() {
        _loginAuthenticationState.value = Authenticating()
        val timer = object : CountDownTimer(1000, 3000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                _loginAuthenticationState.value = Authenticated()
            }
        }
        timer.start()
    }

    fun resetState() {
        _loginAuthenticationState.value = null
    }

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