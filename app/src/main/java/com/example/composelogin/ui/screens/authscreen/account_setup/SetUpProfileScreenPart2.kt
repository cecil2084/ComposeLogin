package com.example.composelogin.ui.screens.authscreen.account_setup

import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composelogin.R
import com.example.composelogin.SetUpNavRoutes
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.viewmodels.SetUpViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SetUpProfileScreenPart2(
    modifier: Modifier = Modifier,
    viewModel: SetUpViewModel = viewModel(),
    navController: NavHostController,
    onConfirmLastClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(LocalStuddyColors.current.primary700)
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            ProgressBar(
                modifier = Modifier.background(LocalStuddyColors.current.primary700),
                progressRatio = 0.4f,
                onBackClick = {

                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SetUpNavRoutes.STRENGTHS,
            Modifier.padding(innerPadding),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            composable(route = SetUpNavRoutes.STRENGTHS) {
                StrengthsScreen(
                    onConfirmClick = {}
                )
            }

            composable(route = SetUpNavRoutes.WEAKNESSES) {

            }

            composable(route = SetUpNavRoutes.PREFERRED_STUDY_TIME) {

            }

            composable(route = SetUpNavRoutes.PREFERRED_FREQUENCY) {

            }

            composable(route = SetUpNavRoutes.PREFERRED_TRAITS) {

            }
        }
    }
}

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    progressRatio: Float = 0f,
    onBackClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(modifier = Modifier.padding(start = 8.dp), onClick = onBackClick) {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.back_button),
                contentDescription = "back button",
                tint = Color.White
            )
        }

        Box(
            modifier = Modifier
                .padding(end = 25.dp)
                .clip(RoundedCornerShape(100.dp))
                .fillMaxWidth()
                .background(LocalStuddyColors.current.darkNeutral600)
                .aspectRatio(10f)
        ) {
            val progressColor: Color = LocalStuddyColors.current.warning700
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRoundRect(
                    color = progressColor,
                    size = Size(this.size.width * progressRatio, this.size.height),
                    cornerRadius = CornerRadius(this.size.height)
                )
            }
        }
    }
}