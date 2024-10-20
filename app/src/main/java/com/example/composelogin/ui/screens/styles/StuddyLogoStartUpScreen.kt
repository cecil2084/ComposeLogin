package com.example.composelogin.ui.screens.styles

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
        painter = painterResource(id = R.drawable.studdy_text_logo_bitmap),
        contentDescription = "studdy logo start up screen",
        modifier = Modifier
//            .padding(vertical = 36.dp)
            .width(296.dp)
            .height(102.dp)
            .border(width = 1.dp, color = LocalStuddyColors.current.primary700)
    )
}

@Composable
fun StuddyLogoStartUpScreenSmaller() {
    Image(
        painter = painterResource(id = R.drawable.studdy_text_logo_bitmap_smaller),
        contentDescription = "studdy logo start up screen smaller",
        modifier = Modifier.width(120.dp).aspectRatio(3f)
    )
}