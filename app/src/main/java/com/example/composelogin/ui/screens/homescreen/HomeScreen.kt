package com.example.composelogin.ui.screens.homescreen

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composelogin.NavRoutes
import com.example.composelogin.R
import com.example.composelogin.model.mockProfile
import com.example.composelogin.ui.screens.styles.StuddyLogoStartUpScreenSmaller
import com.example.composelogin.ui.screens.styles.dimensions.StuddyDimensions
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.viewmodels.HomeViewModel

@Composable
fun MainScreenApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onLogOut: () -> Unit
) {
    Scaffold(
        modifier = modifier.statusBarsPadding(),
        bottomBar = {
            StuddyBottomBar(
                modifier = Modifier
                    .background(
                        LocalStuddyColors.current.primary700
                    )
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                navController = navController
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.HOME,
            Modifier.padding(innerPadding),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            composable(NavRoutes.HOME) {
                HomeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(LocalStuddyColors.current.primary700)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(
                                bottomStart = StuddyDimensions.bottomRoundedRadius,
                                bottomEnd = StuddyDimensions.bottomRoundedRadius
                            )
                        )
                        .padding(top = 20.dp)
                )
            }

            composable(NavRoutes.MATCHES) {
                MatchesScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(LocalStuddyColors.current.primary700)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(
                                bottomStart = StuddyDimensions.bottomRoundedRadius,
                                bottomEnd = StuddyDimensions.bottomRoundedRadius
                            )
                        )
                        .padding(top = 20.dp)
                )
            }

            composable(NavRoutes.MESSAGES) {
                MessagesScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(LocalStuddyColors.current.primary700)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(
                                bottomStart = StuddyDimensions.bottomRoundedRadius,
                                bottomEnd = StuddyDimensions.bottomRoundedRadius
                            )
                        )
                        .padding(top = 20.dp)
                )
            }

            composable(NavRoutes.STUDDY_FEED) {
                StuddyFeedScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(LocalStuddyColors.current.primary700)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(
                                bottomStart = StuddyDimensions.bottomRoundedRadius,
                                bottomEnd = StuddyDimensions.bottomRoundedRadius
                            )
                        )
                        .padding(top = 20.dp)
                )
            }

            composable(NavRoutes.WEEKLY_CHALLENGES) {
                WeeklyChallengeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(LocalStuddyColors.current.primary700)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(
                                bottomStart = StuddyDimensions.bottomRoundedRadius,
                                bottomEnd = StuddyDimensions.bottomRoundedRadius
                            )
                        )
                        .padding(top = 20.dp)
                )
            }

            composable(NavRoutes.USER_PREFS) {
                UserPrefsScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(LocalStuddyColors.current.primary700)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(
                                bottomStart = StuddyDimensions.bottomRoundedRadius,
                                bottomEnd = StuddyDimensions.bottomRoundedRadius
                            )
                        )
                        .padding(top = 20.dp),
                    onClick = onLogOut
                )
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier.fillMaxWidth()
        ) {

//            StuddyBottomBarIcon(onClick = {}) {
//                Icon(
//                    imageVector = ImageVector.vectorResource(id = R.drawable.filter_profiles_btn),
//                    contentDescription = "filter profiles",
//                    modifier = Modifier.size(StuddyDimensions.iconSmall),
//                    tint = LocalStuddyColors.current.primary700
//                )
//            }

            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                StuddyLogoStartUpScreenSmaller()
            }
        }

        SwipeCard {
            Card(
                shape = RoundedCornerShape(StuddyDimensions.cardRoundedRadius),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = StuddyDimensions.shadowElevation
                ),
                modifier = Modifier
                    .padding(StuddyDimensions.cardPadding)
                    .aspectRatio(StuddyDimensions.cardAspectRatio)
            ) {
                UserProfileCard(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(StuddyDimensions.cardRoundedRadius)),
                    userProfileCard = mockProfile
                )
            }
        }
    }
}