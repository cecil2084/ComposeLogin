package com.example.composelogin.model

import java.util.UUID

data class LogInDataRequestModel(
    val LogInMethodId: UUID,
    val Username: String,
    val Password: String
)
