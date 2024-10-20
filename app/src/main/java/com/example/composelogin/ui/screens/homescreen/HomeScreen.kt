package com.example.composelogin.ui.screens.homescreen

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.composelogin.R
import com.example.composelogin.model.mockProfile
import com.example.composelogin.ui.screens.styles.StuddyLogoStartUpScreenSmaller
import com.example.composelogin.ui.screens.styles.dimensions.StuddyDimensions
import com.example.composelogin.ui.theme.LocalStuddyColors

@Composable
fun MainScreenApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.statusBarsPadding(),
        bottomBar = {
            StuddyBottomBar(
                modifier = Modifier
                    .background(
                        LocalStuddyColors.current.primary700
                    )
                    .fillMaxWidth()
                    .navigationBarsPadding()
            )
        }
    ) { innerPadding ->
        HomeScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(LocalStuddyColors.current.primary700)
        )
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.background(
            color = Color.White,
            shape = RoundedCornerShape(
                bottomStart = StuddyDimensions.bottomRoundedRadius,
                bottomEnd = StuddyDimensions.bottomRoundedRadius
            )
        )
    ) {
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier.fillMaxWidth()
        ) {

            StuddyBottomBarIcons(onClick = {}) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.filter_profiles_btn),
                    contentDescription = "filter profiles",
                    modifier = Modifier.size(StuddyDimensions.iconSmall),
                    tint = LocalStuddyColors.current.primary700
                )
            }

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