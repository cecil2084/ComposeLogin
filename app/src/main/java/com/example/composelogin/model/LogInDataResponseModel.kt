package com.example.composelogin.model

import java.util.UUID

data class LogInDataResponseModel(
    val id: UUID,
    val username: String,
    val token: String,
    val expires_at: String,
    val created_at: String
)
