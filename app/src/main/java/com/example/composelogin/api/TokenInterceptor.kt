package com.example.composelogin.api

import okhttp3.Interceptor

object TokenInterceptor : Interceptor {
    var token: String? = null
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val originalRequest = chain.request()

        // Check if token exists
        if (token.isNullOrEmpty()) {
            return chain.proceed(originalRequest) // Proceed without modifying the request
        }

        // Attach the token to the Authorization header
        val updatedRequest = originalRequest.newBuilder()
            .header("authorization", "Bearer $token")
            .build()

        return chain.proceed(updatedRequest)
    }
}