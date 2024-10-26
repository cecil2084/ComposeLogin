package com.example.composelogin.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composelogin.data.skillsList
import com.example.composelogin.model.Skill
import com.example.composelogin.model.SkillListState
import com.example.composelogin.ui.Exceptions.ListLoadingInProgressException
import com.example.composelogin.ui.Exceptions.ListNotLoadedException
import com.example.composelogin.ui.states.SetUpProfileState
import kotlinx.coroutines.Dispatchers
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

    private val _searchQueryState: MutableStateFlow<String> =
        MutableStateFlow("")
    val searchQueryState: StateFlow<String> = _searchQueryState.asStateFlow()

    var skillListState: SkillListState by mutableStateOf(SkillListState.Loading)
        private set

    fun onSearchQueryChanged(updatedText: String){
        _searchQueryState.value = updatedText
    }

    fun nextPage() {
        _uiState.update { currentState ->
            currentState.copy(
                currentPage = currentState.currentPage + 1
            )
        }
    }

    fun previousPage() {
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
        viewModelScope.launch(Dispatchers.IO) {
            skillListState = SkillListState.Loading
            delay(2000L)
            skillListState = try {
                SkillListState.Success(skillsList)
            } catch (e: Exception) {
                SkillListState.Error
            }
        }
    }

    fun removeStrengthSkill(skill: Skill) {
        _uiState.update { currentState ->
            currentState.copy(
                strengths = currentState.strengths.filter {
                    it.id != skill.id
                }
            )
        }
    }

    fun addStrengthSkill(skill: Skill) {
        if (!_uiState.value.strengths.any { it.id == skill.id }) {
            _uiState.update { currentState ->
                currentState.copy(
                    strengths = currentState.strengths + listOf(skill)
                )
            }
        }
    }

    fun removeWeaknessSkill(skill: Skill) {
        _uiState.update { currentState ->
            currentState.copy(
                weaknesses = currentState.weaknesses.filter {
                    it.id != skill.id
                }
            )
        }
    }

    fun addWeaknessSkill(skill: Skill) {
        if (!_uiState.value.weaknesses.any { it.id == skill.id }) {
            _uiState.update { currentState ->
                currentState.copy(
                    weaknesses = currentState.weaknesses + listOf(skill)
                )
            }
        }
    }

    fun filterSuggestion(searchQuery: String): List<Skill> {
        return when (skillListState) {
            is SkillListState.Success ->
                (skillListState as SkillListState.Success).skillList.filter {
                    it.name.contains(
                        searchQuery,
                        ignoreCase = true
                    )
                }
            is SkillListState.Loading -> throw ListLoadingInProgressException("di pa tapos mag load eh")
            is SkillListState.Error -> throw ListNotLoadedException("awww di nag load :(")
        }
    }
}