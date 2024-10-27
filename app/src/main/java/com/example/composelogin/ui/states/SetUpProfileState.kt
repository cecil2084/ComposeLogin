package com.example.composelogin.ui.states

import com.example.composelogin.model.PreferredStudyFrequency
import com.example.composelogin.model.PreferredStudyTime
import com.example.composelogin.model.PreferredTraits
import com.example.composelogin.model.Skill

data class SetUpProfileState (
    val currentPage: Int = 1,
    val strengths : List<Skill> = listOf(),
    val weaknesses: List<Skill> = listOf(),
    val preferredStudyTime: List<SelectionState> = listOf(),
    val preferredStudyFrequency: List<SelectionState> = listOf(),
    val studyPartnerTraits: List<SelectionState> = listOf()
)