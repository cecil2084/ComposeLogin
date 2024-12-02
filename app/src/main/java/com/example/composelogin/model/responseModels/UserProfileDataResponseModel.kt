package com.example.composelogin.model.responseModels

data class UserProfileDataResponseModel (
    val given_name: String? = null,
    val family_name: String? = null,
    val age: Int? = null,
    val sex_id: String? = null,
    val birthday: String? = null,
    val city_id: String? = null,
    val display_photo_url: String? = null,
    val about: String? = null,
    val university_id: String? = null,
    val degree_program_id: String? = null,
    val education_level_id: String? = null,
    val id: String? = null,
    val user_strengths: List<SkillDataResponseModel>? = null,
    val user_weaknesses: List<SkillDataResponseModel>? = null
)