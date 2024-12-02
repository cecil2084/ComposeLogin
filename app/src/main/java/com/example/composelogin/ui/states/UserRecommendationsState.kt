package com.example.composelogin.ui.states

import com.example.composelogin.model.responseModels.UserRecommendationsDataResponseModel

open class UserRecommendationsState {
    object Idle : UserRecommendationsState()
    object Loading : UserRecommendationsState()
    data class Success(var userRecommendations: UserRecommendationsDataResponseModel?) : UserRecommendationsState()
    data class Error(val message: String) : UserRecommendationsState()
}