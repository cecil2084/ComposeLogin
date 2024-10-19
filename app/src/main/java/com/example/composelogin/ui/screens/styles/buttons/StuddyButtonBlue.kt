package com.example.composelogin.ui.screens.styles.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.theme.quicksandFamily

@Composable
fun StuddyButtonBlue(
    content: String,
    onClick: () -> Unit,
    width: Dp = 265.dp,
    enabled: Boolean = true
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        modifier = Modifier
            .width(width),
        contentPadding = PaddingValues(vertical = 12.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonColors(
            containerColor = LocalStuddyColors.current.primary700,
            disabledContainerColor = LocalStuddyColors.current.primary500,
            contentColor = Color.White,
            disabledContentColor = LocalStuddyColors.current.primary700
        )
    ) {
        Text(
            text = content,
            fontFamily = quicksandFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Medium
            )
        )
    }
}