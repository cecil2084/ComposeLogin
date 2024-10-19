package com.example.composelogin.ui.screens.styles

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composelogin.R
import com.example.composelogin.ui.theme.LocalStuddyColors

@Composable
fun StuddyLogoStartUpScreen() {
    Image(
        painter = painterResource(id = R.drawable.studdylogo_with_text_bitmap),
        contentDescription = "studdy logo start up screen",
        modifier = Modifier
//            .padding(vertical = 36.dp)
            .width(296.dp)
            .height(102.dp)
            .border(width = 1.dp, color = LocalStuddyColors.current.primary700)
    )
}