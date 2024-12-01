package com.example.composelogin.model

import android.provider.ContactsContract.CommonDataKinds.Website

data class DegreeProgramResponseModel(
    val id: String,
    val name: String,
    val description: String,
    val created_by: String,
    val created_at: String,
    val last_modified_bv: String,
    val last_modified_at: String
)
