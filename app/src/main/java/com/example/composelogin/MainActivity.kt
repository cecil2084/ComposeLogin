package com.example.composelogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composelogin.ui.theme.ComposeLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLoginTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = NavRoutes.SIGNUP) {
                    composable(NavRoutes.SIGNUP)  { MainSignUpScreen(navController) }
                    composable(NavRoutes.LOGIN) { MainLoginScreen(navController) }
                    composable(NavRoutes.SETUP_PROFILE) { MainProfileDetailsSetUp(navController) }
                }
            }
        }
    }
}

