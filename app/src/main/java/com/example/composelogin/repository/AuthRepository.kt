package com.example.composelogin.repository

//import com.example.composelogin.api.ApiService
//import com.example.composelogin.model.LoginRequest
//import com.example.composelogin.model.UserProfile
//import com.example.composelogin.storage.TokenManager
//import kotlinx.coroutines.flow.first
//import javax.inject.Inject
//
//class AuthRepository @Inject constructor(
//    private val apiService: ApiService,
//    private val tokenManager: TokenManager
//) {
//    suspend fun login(email: String, password: String): String {
//        val response = apiService.login(LoginRequest(email, password))
//        tokenManager.saveToken(response.token)
//        return response.token
//    }
//
//    suspend fun getUserProfile(): UserProfile {
//        val token = tokenManager.authToken.first()
//        requireNotNull(token) { "Token is not available!" }
//        return apiService.getUserProfile("Bearer $token")
//    }
//
//    suspend fun logout() {
//        tokenManager.clearToken()
//    }
//}