package com.example.composelogin.model

import androidx.annotation.DrawableRes

data class UserProfileSwipeDetails(
    @DrawableRes val profilePicture: Int,
    val name: String,
    val age: String,
    val school: String,
    val degreeProgram: String,
    val about: String,
    val academicSkills: List<String>,
    val otherSkills: List<String>
)