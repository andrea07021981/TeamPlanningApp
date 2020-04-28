package com.example.teamplanningapp.constant

sealed class LoginAuthenticationStates(open val message: String)

// Authentication failed
data class Authenticating(override val message: String = "Loggin in") : LoginAuthenticationStates(message)

// Initial state, the user needs to authenticate
data class Unauthenticated(override val message: String = "Not logged in") : LoginAuthenticationStates(message)

// Initial state, the user needs to authenticate
data class Authenticated(override val message: String = "Authentication ok") : LoginAuthenticationStates(message)

// Authentication failed
data class InvalidAuthentication(override val message: String = "Authentication error") : LoginAuthenticationStates(message)
