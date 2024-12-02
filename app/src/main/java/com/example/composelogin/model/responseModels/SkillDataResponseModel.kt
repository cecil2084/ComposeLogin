package com.example.composelogin.model.responseModels

import java.util.Date

data class SkillDataResponseModel(
    val id: String,
    val name: String,
    val description: String,
    val created_by: String,
    val created_at: Date,
    val last_modified_by: String,
    val last_modifier_at: Date
)
