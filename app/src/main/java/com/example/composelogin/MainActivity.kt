package com.example.composelogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.composelogin.ui.screens.homescreen.MainScreenApp
import com.example.composelogin.ui.screens.MainLoginScreen
import com.example.composelogin.ui.screens.MainProfileDetailsSetUp
import com.example.composelogin.ui.screens.MainSignUpScreen
import com.example.composelogin.ui.theme.ComposeLoginTheme
import com.example.composelogin.ui.viewmodels.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLoginTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = NavRoutes.AUTH) {
                    navigation(route = NavRoutes.AUTH, startDestination = NavRoutes.SIGNUP){
                        composable(route = NavRoutes.SIGNUP) {
                            MainSignUpScreen(
                                onSignUpClick = { navController.navigate(NavRoutes.SETUP_PROFILE) },
                                onLoginClick = { navController.navigate(NavRoutes.LOGIN) }
                            )
                        }

                        composable(route = NavRoutes.LOGIN) {
                            MainLoginScreen(
                                onSignUpClick = { navController.navigate(NavRoutes.SIGNUP) },
                            )
                        }

                        composable(route = NavRoutes.SETUP_PROFILE) {
                            MainProfileDetailsSetUp(
                                onSignUpClick = { navController.navigate(NavRoutes.SETUP_PROFILE) },
                                onConfirmClick = {
                                    navController.navigate(NavRoutes.MAIN) {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }
                    }

                    composable(route = NavRoutes.MAIN) {
                        val homeNavController = rememberNavController()
                        MainScreenApp(
                            navController = homeNavController,
                            onLogOut = {
                                navController.navigate(NavRoutes.SIGNUP) {
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

