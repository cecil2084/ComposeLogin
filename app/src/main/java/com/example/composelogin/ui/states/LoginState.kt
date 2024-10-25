package com.example.composelogin.ui.states

data class LoginState(
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false
)
