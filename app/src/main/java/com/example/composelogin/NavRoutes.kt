package com.example.composelogin

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

object NavRoutes {
    const val AUTH: String = "auth"
    const val SIGNUP: String = "signup_screen"
    const val LOGIN: String = "login_screen"
    const val SETUP_PROFILE: String = "setup_profile"

    const val MAIN: String = "main"
    const val MATCHES: String = "matches"
    const val MESSAGES: String = "messages"
    const val HOME: String = "home_screen"
    const val STUDDY_FEED: String = "studdy_feed"
    const val WEEKLY_CHALLENGES: String = "weekly_challenges"
    const val USER_PREFS: String = "user_prefs"
}

data class TopLevelRoute(val name: String, val route: String, @DrawableRes val icon: Int)

val topLevelRoutes = listOf(
    TopLevelRoute("Matches", NavRoutes.MATCHES, R.drawable.toga_bar),
    TopLevelRoute("Messages", NavRoutes.MESSAGES, R.drawable.messages_bar),
    TopLevelRoute("Home", NavRoutes.HOME, R.drawable.swipe_profiles_bar),
    TopLevelRoute("Studdy Feed", NavRoutes.STUDDY_FEED, R.drawable.book),
    TopLevelRoute("Weekly Challenge", NavRoutes.WEEKLY_CHALLENGES, R.drawable.weekly_challenge_bar),
    TopLevelRoute("User Preferences", NavRoutes.USER_PREFS, R.drawable.people_bar)
)