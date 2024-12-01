package com.example.composelogin.ui.viewmodels

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composelogin.api.Cookie
import com.example.composelogin.api.RetrofitClient
import com.example.composelogin.api.Token
import com.example.composelogin.model.LogInDataRequestModel
import com.example.composelogin.model.UserProfileDataResponseModel
import com.example.composelogin.ui.states.LoginUiState
import com.example.composelogin.ui.states.UserProfileState
import com.example.composelogin.ui.states.UserRecommendationsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class HomeViewModel : ViewModel() {
    private val _userProfileState: MutableStateFlow<UserProfileState> =
        MutableStateFlow(UserProfileState())
    val userProfileState: StateFlow<UserProfileState> get() = _userProfileState

    val _userRecommendationState: MutableStateFlow<UserRecommendationsState> =
        MutableStateFlow(
            UserRecommendationsState()
        )
    val userRecommendationState: StateFlow<UserRecommendationsState> = _userRecommendationState.asStateFlow()

    init {
        getUserProfile()
        getUserRecommendations()
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            _userProfileState.value = UserProfileState.Loading
            val toke = Token.token ?: "null"
            try {
                val response = RetrofitClient.loginService.getUserProfile(
                    cookie = Cookie.cookie,
                    token = "Bearer $toke"
                )
                if (response.isSuccessful) {
                    _userProfileState.value =
                        UserProfileState.Success(response.body() ?: UserProfileDataResponseModel())
                } else {
//                    _userProfileState.value = UserProfileState.Error(response.toString())
                    _userProfileState.value = UserProfileState.Error(Token.token ?: "null")
                }
            } catch (e: Exception) {
                _userProfileState.value = UserProfileState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun getUserRecommendations() {
        viewModelScope.launch {
            _userRecommendationState.value = UserRecommendationsState.Loading
            val toke = Token.token ?: "null"
            try {
                val response = RetrofitClient.loginService.getRecommendations(
                    cookie = Cookie.cookie,
                    token = "Bearer $toke"
                )
                if (response.isSuccessful) {
                    _userRecommendationState.value =
                        UserRecommendationsState.Success(response.body())
                } else {
//                    _userProfileState.value = UserProfileState.Error(response.toString())
                    _userRecommendationState.value =
                        UserRecommendationsState.Error(Token.token ?: "null")
                }
            } catch (e: Exception) {
                _userRecommendationState.value =
                    UserRecommendationsState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun removeLastRecommendation() {
        _userRecommendationState.value =
            UserRecommendationsState.Success(
                (_userRecommendationState.value as UserRecommendationsState.Success).userRecommendations?.copy(
                    items = (_userRecommendationState.value as UserRecommendationsState.Success).userRecommendations?.items?.dropLast(1)?: listOf()
                )
            )

//        if (_userRecommendationState.value is UserRecommendationsState.Success){
//            (_userRecommendationState.value as UserRecommendationsState.Success).userRecommendations?.copy(
//                items = (_userRecommendationState.value as UserRecommendationsState.Success).userRecommendations?.items?.dropLast(1)?: listOf()
//            )
//        }
    }

    suspend fun getUniversity(id: String): String {
        return try {
            val response = RetrofitClient.loginService.getUniversity(id, cookie = Cookie.cookie)
            if (response.isSuccessful)
                response.body()?.name ?: "null"
            else
                response.toString()
        } catch (e: Exception) {
            e.message ?: "Unknown Error"
        }
    }

    suspend fun getDegreeProgram(id: String): String {
        return try {
            val response = RetrofitClient.loginService.getDegreeProgram(id, cookie = Cookie.cookie)
            if (response.isSuccessful)
                response.body()?.name ?: "null"
            else
                response.toString()
        } catch (e: Exception) {
            e.message ?: "Unknown Error"
        }
    }
}