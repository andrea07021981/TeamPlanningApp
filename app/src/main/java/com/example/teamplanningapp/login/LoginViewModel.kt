package com.example.teamplanningapp.login

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.*
import com.example.teamplanningapp.constant.*
import com.example.teamplanningapp.data.source.repository.LoginDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel private constructor(
    dataRepository: LoginDataRepository
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

    private fun doLogin()  = viewModelScope.launch{
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
    class LoginViewModelFactory(
        private val dataRepository: LoginDataRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LoginViewModel(dataRepository) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}