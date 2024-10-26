package com.example.composelogin.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composelogin.data.skillsList
import com.example.composelogin.model.Skill
import com.example.composelogin.model.SkillListState
import com.example.composelogin.ui.enums.PageDirection
import com.example.composelogin.ui.states.SetUpProfileState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SetUpViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<SetUpProfileState> =
        MutableStateFlow(SetUpProfileState())
    val uiState: StateFlow<SetUpProfileState> = _uiState.asStateFlow()

    var skillListState: SkillListState by mutableStateOf(SkillListState.Loading)
        private set

    private val _pageDirectionState: MutableStateFlow<PageDirection> =
        MutableStateFlow(PageDirection.FORWARD)
    val pageDirectionState: StateFlow<PageDirection> = _pageDirectionState.asStateFlow()

    fun nextPage() {
        _pageDirectionState.value = PageDirection.FORWARD
        _uiState.update { currentState ->
            currentState.copy(
                currentPage = currentState.currentPage + 1
            )
        }
    }

    fun previousPage() {
        _pageDirectionState.value = PageDirection.BACKWARD
        _uiState.update { currentState ->
            currentState.copy(
                currentPage = currentState.currentPage - 1
            )
        }
    }

    init {
        fetchSkills()
    }

    fun fetchSkills() {
        viewModelScope.launch {
            skillListState = SkillListState.Loading
            delay(2000L)
            skillListState = try {
                SkillListState.Success(skillsList)
            } catch (e: Exception) {
                SkillListState.Error
            }
        }
    }

    fun removeSkill(skill: Skill) {
        _uiState.update { currentState ->
            currentState.copy(
                strengths = currentState.strengths.filter {
                    it.id != skill.id
                }
            )
        }
    }

    fun addSkill(skill: Skill) {
        if (!_uiState.value.strengths.any { it.id == skill.id }) {
            _uiState.update { currentState ->
                currentState.copy(
                    strengths = currentState.strengths + listOf(skill)
                )
            }
        }
    }
}