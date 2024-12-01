package com.example.composelogin.api

import com.example.composelogin.model.DegreeProgramResponseModel
import com.example.composelogin.model.LogInDataRequestModel
import com.example.composelogin.model.LogInDataResponseModel
import com.example.composelogin.model.UniversitiesResponseModel
import com.example.composelogin.model.UserProfileDataResponseModel
import com.example.composelogin.model.UserRecommendationsDataResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface LoginService {
    @POST("/api/v0/auth/login")
    suspend fun login(
        @Body loginRequest: LogInDataRequestModel,
        @Header("Cookie") cookie: String
    ): Response<LogInDataResponseModel>

    @GET("/api/v0/me/profile")
    suspend fun getUserProfile(
        @Header("Cookie") cookie: String,
        @Header("Authorization") token: String
    ): Response<UserProfileDataResponseModel>

    @GET("/api/v0/me/recommendations")
    suspend fun getRecommendations(
        @Header("Cookie") cookie: String,
        @Header("Authorization") token: String
    ): Response<UserRecommendationsDataResponseModel>

    @GET("/api/v0/universities/{id}")
    suspend fun getUniversity(
        @Path("id") id: String,
        @Header("Cookie") cookie: String
    ): Response<UniversitiesResponseModel>

    @GET("/api/v0/degree_programs/{id}")
    suspend fun getDegreeProgram(
        @Path("id") id: String,
        @Header("Cookie") cookie: String
    ): Response<DegreeProgramResponseModel>
}
