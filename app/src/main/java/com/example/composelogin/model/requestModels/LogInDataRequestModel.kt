package com.example.composelogin.model.requestModels

import java.util.UUID

data class LogInDataRequestModel(
    val logInMethodId: UUID,
    val username: String,
    val password: String
)
