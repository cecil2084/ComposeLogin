package com.example.composelogin.api

import com.example.composelogin.model.responseModels.DegreeProgramResponseModel
import com.example.composelogin.model.requestModels.LogInDataRequestModel
import com.example.composelogin.model.requestModels.SwipeRecommendationDataRequestModel
import com.example.composelogin.model.responseModels.LogInDataResponseModel
import com.example.composelogin.model.responseModels.SwipeRecommendationResponseModel
import com.example.composelogin.model.responseModels.UniversitiesResponseModel
import com.example.composelogin.model.responseModels.UserProfileDataResponseModel
import com.example.composelogin.model.responseModels.UserRecommendationsDataResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

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

    @POST("api/v0/me/recommendations/{id}")
    suspend fun matchUser(
        @Body matchRequest: SwipeRecommendationDataRequestModel,
        @Path("id") id: String,
        @Header("Cookie") cookie: String,
        @Header("Authorization") token: String
    ): Response<SwipeRecommendationResponseModel>

    @GET("api/v0/me/recommendations/matched")
    suspend fun getUserMatched(
        @Header("Cookie") cookie: String,
        @Header("Authorization") token: String
    ): Response<UserRecommendationsDataResponseModel>
}
