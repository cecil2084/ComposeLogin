package com.example.composelogin.model

import java.util.UUID

data class LogInDataRequestModel(
    val logInMethodId: UUID,
    val username: String,
    val password: String
)
