package com.example.composelogin.model

import androidx.annotation.DrawableRes
import com.example.composelogin.R

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

val mockProfile = UserProfileSwipeDetails(
    profilePicture = R.drawable.race_pfp,
    name = "Hailey Aquino",
    age = "20",
    degreeProgram = "BS Information Technology",
    about = "Hey, I'm Hailey—an IT student with a big love for all things tech! I'm that person who's always up for a challenge, whether it's fixing a computer glitch or exploring the latest cybersecurity trends.",
    academicSkills = listOf<String>(
        "Python",
        "Web Development",
        "Calculus",
        "Java",
        "Networking",
        "DevOps",
        "Database Management",
        "Cybersecurity"
    ),
    otherSkills = listOf<String>(
        "Painting",
        "Cartooning",
        "Sculpture",
        "Fencing",
        "Graphics Design",
        "Table Tennis",
        "Calligraphy",
        "Music Composition",
        "Culinary Arts",
        "Basketball"
    ),
    school = "Far Eastern University"
)