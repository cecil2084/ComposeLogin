package com.example.composelogin.ui.screens.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composelogin.ui.theme.LocalStuddyColors

@Composable
fun HomeScreenApp(modifier: Modifier = Modifier) {
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
        StuddySwipeMenu(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun StuddySwipeMenu(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Box(
            contentAlignment = Alignment.CenterEnd
        ) {
            Box(){

            }
        }

        SwipeCard {
            UserProfileCard()
        }
    }
}