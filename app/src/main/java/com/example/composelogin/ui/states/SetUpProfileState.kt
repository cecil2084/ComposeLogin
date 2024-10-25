package com.example.composelogin.ui.states

import com.example.composelogin.model.Skill

data class SetUpProfileState (
    val currentPage: Int = 1,
    val strengths : List<Skill> = listOf(),
    val weaknesses: List<Skill> = listOf(),
    val preferredStudyTime: List<String> = listOf(),
    val preferredStudyFrequency: List<String> = listOf(),
    val studyPartnerTraits: List<String> = listOf()
)