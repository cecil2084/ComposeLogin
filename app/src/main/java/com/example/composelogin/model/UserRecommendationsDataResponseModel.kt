package com.example.composelogin.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

data class UserRecommendationsDataResponseModel (
    val page_number: Int,
    val page_size: Int,
    val page_count: Int,
    val items: List<UserProfileDataResponseModel>
)

