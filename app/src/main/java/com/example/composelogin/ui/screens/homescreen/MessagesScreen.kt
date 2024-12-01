package com.example.composelogin.ui.screens.homescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composelogin.ui.states.UserProfileState
import com.example.composelogin.ui.states.UserRecommendationsState
import com.example.composelogin.ui.viewmodels.HomeViewModel

@Composable
fun MessagesScreen(modifier : Modifier = Modifier, viewModel: HomeViewModel = viewModel()){
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = modifier
//    ){
//        Text("MESSAGES SCREEN UNDER CONSTRUCTION")
//    }
    Column (
        modifier = modifier
    ){
//        val uiState by viewModel.userRecommendationState.collectAsState()
//        when (uiState) {
//            is UserRecommendationsState.Loading -> CircularProgressIndicator()
//            is UserRecommendationsState.Error -> Text("Error: ${(uiState as UserRecommendationsState.Error).message}")
//            is UserRecommendationsState.Success -> {
////                Text((uiState as UserRecommendationsState.Success).dirtySummary)
//            }
//        }
    }
}