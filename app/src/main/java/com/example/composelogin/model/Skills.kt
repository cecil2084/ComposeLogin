package com.example.composelogin.model

data class Skill(
    val id: Int,
    val name: String
)

sealed interface SkillListState{
    data class Success(val skillList: List<Skill>) : SkillListState
    data object Error : SkillListState
    data object Loading : SkillListState
}
