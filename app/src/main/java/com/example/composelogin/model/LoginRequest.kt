package com.example.composelogin.model

data class LoginRequest (
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String?,
    val success: Boolean
)
