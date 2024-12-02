package com.example.composelogin.ui.states

import com.example.composelogin.model.responseModels.UserProfileDataResponseModel

open class UserProfileState {
    object Idle : UserProfileState()
    object Loading : UserProfileState()
    data class Success(val userProfile: UserProfileDataResponseModel) : UserProfileState()
    data class Error(val message: String) : UserProfileState()
}