package com.example.composelogin.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.composelogin.ui.states.SetUpProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SetUpViewModel : ViewModel(){
    private val _uiState: MutableStateFlow<SetUpProfileState> = MutableStateFlow(SetUpProfileState())
    val uiState: StateFlow<SetUpProfileState> = _uiState.asStateFlow()

    fun nextPage(){
        _uiState.update { currentState ->
            currentState.copy(
                currentPage = currentState.currentPage + 1
            )
        }
    }

    fun previousPage(){
        _uiState.update { currentState ->
            currentState.copy(
                currentPage = currentState.currentPage - 1
            )
        }
    }
}