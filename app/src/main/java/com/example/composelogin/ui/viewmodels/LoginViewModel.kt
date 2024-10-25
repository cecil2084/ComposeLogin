package com.example.composelogin.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.composelogin.ui.states.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {
    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun onEmailChange(updatedEmail: String){
        _loginState.value = _loginState.value.copy(email = updatedEmail)
    }

    fun onPasswordChange(updatedPassword: String){
        _loginState.value = _loginState.value.copy(password = updatedPassword)
    }

    fun onRememberMeChange(){
        _loginState.update { currentState ->
            currentState.copy(rememberMe = !currentState.rememberMe)
        }
    }
}