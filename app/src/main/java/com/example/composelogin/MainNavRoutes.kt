package com.example.composelogin

import androidx.annotation.DrawableRes

object AuthNavRoutes {
    const val AUTH: String = "auth"
    const val SIGNUP: String = "signup_screen"
    const val LOGIN: String = "login_screen"
    const val SETUP_PROFILE: String = "setup_profile"
}

object SetUpNavRoutes {
    const val SETUP_PROFILE_PART2: String = "set_up_profile_part_2"
    const val STRENGTHS: String = "strengths"
    const val WEAKNESSES: String = "weaknesses"
    const val PREFERRED_STUDY_TIME: String = "preferred_study_time"
    const val PREFERRED_FREQUENCY: String = "preferred_frequency"
    const val PREFERRED_TRAITS: String = "preferred_traits"
}

object MainNavRoutes {
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
    TopLevelRoute("Matches", MainNavRoutes.MATCHES, R.drawable.toga_bar),
    TopLevelRoute("Messages", MainNavRoutes.MESSAGES, R.drawable.messages_bar),
    TopLevelRoute("Home", MainNavRoutes.HOME, R.drawable.swipe_profiles_bar),
    TopLevelRoute("Studdy Feed", MainNavRoutes.STUDDY_FEED, R.drawable.book),
    TopLevelRoute("Weekly Challenge", MainNavRoutes.WEEKLY_CHALLENGES, R.drawable.weekly_challenge_bar),
    TopLevelRoute("User Preferences", MainNavRoutes.USER_PREFS, R.drawable.people_bar)
)