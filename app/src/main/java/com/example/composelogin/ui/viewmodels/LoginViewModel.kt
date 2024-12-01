package com.example.composelogin.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composelogin.api.Cookie
import com.example.composelogin.api.RetrofitClient
import com.example.composelogin.api.Token
import com.example.composelogin.model.LogInDataRequestModel
import com.example.composelogin.ui.states.LoginState
import com.example.composelogin.ui.states.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class LoginViewModel() : ViewModel() {
    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun onEmailChange(updatedEmail: String){
        _loginState.value = _loginState.value.copy(username = updatedEmail)
    }

    fun onPasswordChange(updatedPassword: String){
        _loginState.value = _loginState.value.copy(password = updatedPassword)
    }


    private val _loginUiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> get() = _loginUiState

    fun login(uuid: UUID, username: String, password: String) {
        viewModelScope.launch {
            _loginUiState.value = LoginUiState.Loading
            try {
                val response = RetrofitClient.loginService.login(
                    loginRequest = LogInDataRequestModel(uuid, username, password),
                    cookie = Cookie.cookie
                )
                if (response.isSuccessful) {
                    _loginUiState.value = LoginUiState.Success(response.body()?.token ?: "")
                    Token.token = response.body()?.token
                } else {
                    _loginUiState.value = LoginUiState.Error("Wrong Username or Password")
                }
            } catch (e: Exception) {
                _loginUiState.value = LoginUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

}
