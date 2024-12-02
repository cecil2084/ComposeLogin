package com.example.composelogin.model.responseModels

data class UserRecommendationsDataResponseModel (
    val page_number: Int,
    val page_size: Int,
    val page_count: Int,
    val items: List<UserProfileDataResponseModel>
)

