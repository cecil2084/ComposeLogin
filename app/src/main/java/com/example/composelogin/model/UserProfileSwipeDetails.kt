package com.example.composelogin.model

import com.example.composelogin.model.responseModels.SkillDataResponseModel

data class UserProfileSwipeDetails(
    val profilePicture: String,
    val name: String,
    val age: String,
    val school: String,
    val degreeProgram: String,
    val about: String,
    val academicSkills: List<SkillDataResponseModel>,
    val otherSkills: List<SkillDataResponseModel>
)