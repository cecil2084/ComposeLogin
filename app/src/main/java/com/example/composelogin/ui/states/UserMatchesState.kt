package com.example.composelogin.ui.states

import com.example.composelogin.model.responseModels.UserRecommendationsDataResponseModel

open class UserMatchesState {
    object Idle : UserMatchesState()
    object Loading : UserMatchesState()
    data class Success(val userMatches: UserRecommendationsDataResponseModel?) : UserMatchesState()
    data class Error(val message: String) : UserMatchesState()
}