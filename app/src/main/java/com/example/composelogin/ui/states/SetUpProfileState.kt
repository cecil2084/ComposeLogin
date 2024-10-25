package com.example.composelogin.ui.states

data class SetUpProfileState (
    val currentPage: Int = 1,
    val strengths : List<String> = listOf(),
    val weaknesses: List<String> = listOf(),
    val preferredStudyTime: List<String> = listOf(),
    val preferredStudyFrequency: List<String> = listOf(),
    val studyPartnerTraits: List<String> = listOf()
)