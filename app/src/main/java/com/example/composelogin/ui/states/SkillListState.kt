package com.example.composelogin.ui.states

import com.example.composelogin.model.Skill

sealed interface SkillListState{
    data class Success(val skillList: List<Skill>) : SkillListState
    data object Error : SkillListState
    data object Loading : SkillListState
}