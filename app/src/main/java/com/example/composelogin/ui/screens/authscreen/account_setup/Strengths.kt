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
fun StrengthsScreen(
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
        Text("ScreenA")
    }

    StuddyButtonWhite(
        stringResource(R.string.Confirm),
        onClick = onConfirmClick,
    )
}

//@Composable
//fun StrengthsScreen(
//    onConfirmClick: () -> Unit,
//    onBackClick: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Column(
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier
//            .fillMaxSize()
//            .background(LocalStuddyColors.current.primary700)
//            .statusBarsPadding()
//            .navigationBarsPadding()
//    ) {
//        ProgressBar(progressRatio = 0.4f)
//
//        MockDisplay(modifier.weight(1f))
//
//        StuddyButtonWhite(
//            stringResource(R.string.Confirm),
//            onClick = {},
//        )
//    }
//}
