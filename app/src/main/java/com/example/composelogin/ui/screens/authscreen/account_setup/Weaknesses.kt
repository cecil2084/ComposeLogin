package com.example.composelogin.ui.screens.authscreen.account_setup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.composelogin.R
import com.example.composelogin.ui.screens.styles.buttons.StuddyButtonWhite

@Composable
fun WeaknessesScreen(
    modifier: Modifier = Modifier,
    onConfirmClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .background(Color.Gray)
            .fillMaxSize()
    ) {
        Text("Weaknesses")
        StuddyButtonWhite(
            stringResource(R.string.Confirm),
            onClick = onConfirmClick,
        )
    }
}