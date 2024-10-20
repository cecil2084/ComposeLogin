package com.example.composelogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.composelogin.ui.screens.homescreen.MainScreenApp
import com.example.composelogin.ui.screens.MainLoginScreen
import com.example.composelogin.ui.screens.MainProfileDetailsSetUp
import com.example.composelogin.ui.screens.MainSignUpScreen
import com.example.composelogin.ui.theme.ComposeLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLoginTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = NavRoutes.AUTH) {

                    navigation(
                        startDestination = NavRoutes.SIGNUP,
                        route = NavRoutes.AUTH
                    ) {
                        composable(NavRoutes.SIGNUP) {
                            MainSignUpScreen(
                                onSignUpClick = { navController.navigate(NavRoutes.SETUP_PROFILE) },
                                onLoginClick = { navController.navigate(NavRoutes.LOGIN) }
                            )
                        }
                        composable(NavRoutes.LOGIN) {
                            MainLoginScreen(
                                onSignUpClick = { navController.navigate(NavRoutes.SIGNUP) }
                            )
                        }
                        composable(NavRoutes.SETUP_PROFILE) {
                            MainProfileDetailsSetUp(
                                onConfirmClick = { navController.navigate(NavRoutes.MAIN){
                                    popUpTo(NavRoutes.AUTH){
                                        inclusive = true
                                    }
                                } },
                                onSignUpClick = { navController.navigate(NavRoutes.SIGNUP) }
                            )
                        }
                    }

                    navigation(
                        startDestination = NavRoutes.HOME,
                        route = NavRoutes.MAIN
                    ){
                        composable(NavRoutes.HOME) {
                            MainScreenApp()
                        }
                    }
                }
            }
        }
    }
}

