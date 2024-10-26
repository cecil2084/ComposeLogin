package com.example.composelogin.ui.screens.authscreen.account_setup

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.composelogin.AuthNavRoutes
import com.example.composelogin.R
import com.example.composelogin.model.TOTAL_PAGE
import com.example.composelogin.ui.enums.PageDirection
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.viewmodels.SetUpViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SetUpProfileScreenPart2(
    modifier: Modifier = Modifier,
    viewModel: SetUpViewModel = viewModel(),
    onConfirmLastClick: () -> Unit,
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()
    val pageDirectionState by viewModel.pageDirectionState.collectAsState()

    Scaffold(
        modifier = modifier
            .background(LocalStuddyColors.current.primary700)
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            ProgressBar(
                modifier = Modifier.background(LocalStuddyColors.current.primary700),
                progressRatio = uiState.currentPage / TOTAL_PAGE.toFloat(),
                onBackClick = {
                    if (uiState.currentPage != 1) {
                        viewModel.previousPage()
                    } else {
                        navController.navigate(AuthNavRoutes.SIGNUP)
                    }
                }
            )
        }
    ) { innerPadding ->

        AnimatedContent(
            modifier = Modifier.background(LocalStuddyColors.current.primary700),
            targetState = uiState.currentPage,
            transitionSpec = {
                if (pageDirectionState.equals(PageDirection.FORWARD)) {
                    (slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }) + fadeIn()).togetherWith(
                        slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }) + fadeOut()
                    )
                } else {
                    (slideInHorizontally(initialOffsetX = { fullWidth -> -fullWidth }) + fadeIn()).togetherWith(
                        slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth }) + fadeOut()
                    )
                }
            }, label = ""
        ) { page ->
            when (page) {
                1 -> StrengthsScreen(
                    skillListState = viewModel.skillListState,
                    skillSet = uiState.strengths,
                    modifier = Modifier.padding(innerPadding),
                    onConfirmClick = { viewModel.nextPage() },
                    onSkillSelect = { viewModel.addSkill(it) },
                    onSkillRemove = { viewModel.removeSkill(it) }
                    )

                2 -> WeaknessesScreen(
                    modifier = Modifier.padding(innerPadding),
                    onConfirmClick = { viewModel.nextPage() })

                3 -> PreferredStudyTimeScreen(
                    modifier = Modifier.padding(innerPadding),
                    onConfirmClick = { viewModel.nextPage() })

                4 -> PreferredStudyFrequency(
                    modifier = Modifier.padding(innerPadding),
                    onConfirmClick = { viewModel.nextPage() }
                )

                5 -> PreferredTraits(
                    modifier = Modifier.padding(innerPadding),
                    onConfirmClick = onConfirmLastClick,
                    )
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
    val ratio by animateFloatAsState(
        targetValue = progressRatio,
        animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing),
        label = "ratio animation"
    )
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
                .aspectRatio(15f)
        ) {
            val progressColor: Color = LocalStuddyColors.current.accent2700
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRoundRect(
                    color = progressColor,
                    size = Size(this.size.width * ratio, this.size.height),
                    cornerRadius = CornerRadius(this.size.height)
                )
            }
        }
    }
}