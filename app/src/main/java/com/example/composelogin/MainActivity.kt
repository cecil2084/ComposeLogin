package com.example.composelogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.composelogin.ui.screens.authscreen.MainLoginScreen
import com.example.composelogin.ui.screens.authscreen.account_setup.MainProfileDetailsSetUp
import com.example.composelogin.ui.screens.authscreen.MainSignUpScreen
import com.example.composelogin.ui.screens.authscreen.account_setup.SetUpProfileScreenPart2
import com.example.composelogin.ui.screens.homescreen.MainScreenApp
import com.example.composelogin.ui.theme.ComposeLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLoginTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = AuthNavRoutes.AUTH) {
                    navigation(route = AuthNavRoutes.AUTH, startDestination = AuthNavRoutes.SIGNUP) {
                        composable(route = AuthNavRoutes.SIGNUP) {
                            MainSignUpScreen(
                                onSignUpClick = { navController.navigate(AuthNavRoutes.SETUP_PROFILE) },
                                onLoginClick = { navController.navigate(AuthNavRoutes.LOGIN) }
                            )
                        }

                        composable(route = AuthNavRoutes.LOGIN) {
                            MainLoginScreen(
                                onSignUpClick = { navController.navigate(AuthNavRoutes.SIGNUP) },
                            )
                        }

                        composable(route = AuthNavRoutes.SETUP_PROFILE) {
                            MainProfileDetailsSetUp(
                                onSignUpClick = { navController.navigate(AuthNavRoutes.SIGNUP) },
                                onConfirmClick = {
                                    navController.navigate(SetUpNavRoutes.SETUP_PROFILE_PART2) {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable(route = SetUpNavRoutes.SETUP_PROFILE_PART2) {
                            SetUpProfileScreenPart2(
                                onConfirmLastClick = {navController.navigate(MainNavRoutes.MAIN)},
                                navController = navController
                            )
                        }
                    }

                    composable(route = MainNavRoutes.MAIN) {
                        val homeNavController = rememberNavController()
                        MainScreenApp(
                            navController = homeNavController,
                            onLogOut = {
                                navController.navigate(AuthNavRoutes.SIGNUP) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

