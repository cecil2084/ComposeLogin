package com.example.composelogin.ui.states

data class LoginState(
    val username: String = "",
    val password: String = "",
    val token: String? = null
)