package com.example.composelogin.ui.screens.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun UserPrefsScreen(modifier : Modifier = Modifier, onClick: () -> Unit){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        Text("USER PREFS SCREEN UNDER CONSTRUCTION")
        Button(onClick = onClick) {
            Text("Log out")
        }
    }
}