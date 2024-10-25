package com.example.composelogin.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.composelogin.ui.states.LoginState
import com.example.composelogin.ui.states.SignUpState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel : ViewModel() {
    private val _signUpState: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState())
    val signUpState: StateFlow<SignUpState> = _signUpState.asStateFlow()

    fun onUsernameChange(updatedUsername: String){
        _signUpState.value = _signUpState.value.copy(username = updatedUsername)
    }

    fun onEmailChange(updatedEmail: String){
        _signUpState.value = _signUpState.value.copy(email = updatedEmail)
    }

    fun onPasswordChange(updatedPassword: String){
        _signUpState.value = _signUpState.value.copy(password = updatedPassword)
    }

    fun authenticateSignUp(){

    }
}