package com.example.composelogin.ui.screens.homescreen

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composelogin.MainNavRoutes
import com.example.composelogin.model.UserProfileSwipeDetails
import com.example.composelogin.ui.screens.styles.StuddyLogoStartUpScreenSmaller
import com.example.composelogin.ui.screens.styles.dimensions.StuddyDimensions
import com.example.composelogin.ui.states.UserRecommendationsState
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.theme.StuddyTypography
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
            startDestination = MainNavRoutes.HOME,
            Modifier.padding(innerPadding),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            composable(MainNavRoutes.HOME) {
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

            composable(MainNavRoutes.MATCHES) {
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

//            composable(MainNavRoutes.MESSAGES) {
//                MessagesScreen(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(LocalStuddyColors.current.primary700)
//                        .background(
//                            color = Color.White,
//                            shape = RoundedCornerShape(
//                                bottomStart = StuddyDimensions.bottomRoundedRadius,
//                                bottomEnd = StuddyDimensions.bottomRoundedRadius
//                            )
//                        )
//                        .padding(top = 20.dp)
//                        .clip(
//                            shape = RoundedCornerShape(
//                                bottomStart = StuddyDimensions.bottomRoundedRadius,
//                                bottomEnd = StuddyDimensions.bottomRoundedRadius
//                            )
//                        )
//                        .verticalScroll(rememberScrollState())
//                )
//            }
//
//            composable(MainNavRoutes.STUDDY_FEED) {
//                StuddyFeedScreen(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(LocalStuddyColors.current.primary700)
//                        .background(
//                            color = Color.White,
//                            shape = RoundedCornerShape(
//                                bottomStart = StuddyDimensions.bottomRoundedRadius,
//                                bottomEnd = StuddyDimensions.bottomRoundedRadius
//                            )
//                        )
//                        .padding(top = 20.dp)
//                )
//            }
//
//            composable(MainNavRoutes.WEEKLY_CHALLENGES) {
//                WeeklyChallengeScreen(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(LocalStuddyColors.current.primary700)
//                        .background(
//                            color = Color.White,
//                            shape = RoundedCornerShape(
//                                bottomStart = StuddyDimensions.bottomRoundedRadius,
//                                bottomEnd = StuddyDimensions.bottomRoundedRadius
//                            )
//                        )
//                        .padding(top = 20.dp)
//                )
//            }

            composable(MainNavRoutes.USER_PREFS) {
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
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = viewModel()) {

    val uiState by viewModel.userRecommendationState.collectAsState()

    Column(
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                StuddyLogoStartUpScreenSmaller()
            }
        }

        Box {
            when (uiState) {
                is UserRecommendationsState.Loading -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Loading",
                            color = Color.LightGray,
                            style = StuddyTypography.hL
                        )
                        CircularProgressIndicator()
                    }
                }
                is UserRecommendationsState.Error -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Error Loading Recommendations :-(",
                            color = Color.LightGray,
                            style = StuddyTypography.hL
                        )
                    }
                }
                is UserRecommendationsState.Success -> {
                    if ((uiState as UserRecommendationsState.Success).userRecommendations?.items?.isNotEmpty() == true)
                        (uiState as UserRecommendationsState.Success).userRecommendations?.items?.forEachIndexed() {index, it ->
                            var school by remember { mutableStateOf("Loading...") }
                            var degreeProgram by remember { mutableStateOf("Loading...") }

                            LaunchedEffect(true) {
                                school = viewModel.getUniversity(it.university_id ?: "null")
                                degreeProgram =
                                    viewModel.getDegreeProgram(it.degree_program_id ?: "null")
                            }

                            val userProfile = UserProfileSwipeDetails(
                                name = it.given_name + " " + it.family_name,
                                age = it.age.toString(),
                                school = school,
                                degreeProgram = degreeProgram,
                                about = it.about ?: "null",
                                academicSkills = it.user_strengths ?: listOf(),
                                otherSkills = it.user_weaknesses ?: listOf(),
                                profilePicture = it.display_photo_url ?: "null"
                            )

                            SwipeCard(
                                onSwipeRight = {
                                    viewModel.removeLastRecommendation()
                                },
                                onSwipeLeft = {
                                    viewModel.removeLastRecommendation()
                                }
                            ) {
                                Card(
                                    shape = RoundedCornerShape(StuddyDimensions.cardRoundedRadius),
                                    elevation = CardDefaults.cardElevation(
                                        defaultElevation = if (index == 0) StuddyDimensions.shadowElevation else 0.dp
                                    ),
                                    modifier = Modifier
                                        .padding(StuddyDimensions.cardPadding)
                                        .aspectRatio(StuddyDimensions.cardAspectRatio)
                                ) {
                                    UserProfileCard(
                                        modifier = Modifier
                                            .clip(shape = RoundedCornerShape(StuddyDimensions.cardRoundedRadius)),
                                        userProfileCard = userProfile,
                                        onAccept = {
                                            viewModel.onAcceptUser(it.id?: "null", true)
                                            viewModel.removeLastRecommendation()
                                        },
                                        onReject = {
                                            viewModel.onAcceptUser(it.id?: "null", false)
                                            viewModel.removeLastRecommendation()
                                        }
                                    )
                                }
                            }
                        }
                    else {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "Nothing to Show ;-)",
                                color = Color.LightGray,
                                style = StuddyTypography.hL
                            )
                        }
                    }
                }
            }
        }
    }
}