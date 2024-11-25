package com.example.composelogin.api

import com.example.composelogin.model.LogInDataRequestModel
import com.example.composelogin.model.LogInDataResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginService {
    @POST("api/v0/auth/login")
    suspend fun login(
        @Body loginRequest: LogInDataRequestModel,
        @Header("Cookie") cookie: String
    ): Response<LogInDataResponseModel>
}

