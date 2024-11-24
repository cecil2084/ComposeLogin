package com.example.composelogin.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.composelogin.repository.AuthRepository
import com.example.composelogin.ui.states.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
//    private val authRepository: AuthRepository
): ViewModel() {

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

//    fun login(email: String, password: String) {
//        viewModelScope.launch {
//            try {
//                val token = authRepository.login(email, password)
//                _loginState.value = _loginState.value.copy(token = "Login successful: $token")
//            } catch (e: Exception) {
//                _loginState.value = _loginState.value.copy(token = "Login failed: ${e.message}")
//            }
//        }
//    }
//
//    fun logout() {
//        viewModelScope.launch {
//            authRepository.logout()
//            _loginState.value = _loginState.value.copy(token = null)
//        }
//    }

}