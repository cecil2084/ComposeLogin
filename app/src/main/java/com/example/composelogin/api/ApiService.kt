package com.example.composelogin.api

import com.example.composelogin.model.AuthResponse
import com.example.composelogin.model.LoginRequest
import com.example.composelogin.model.UserProfile
import retrofit2.http.*

//data class LoginRequest(val email: String, val password: String)
//data class LoginResponse(val token: String)

interface ApiService {
    @POST("api/v0/auth")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @GET("user/profile")
    suspend fun getUserProfile(@Header("Authorization") token: String): UserProfile
}