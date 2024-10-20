package com.example.composelogin.ui.screens.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.composelogin.ui.screens.styles.dimensions.StuddyDimensions
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.theme.StuddyTypography

@Composable
fun ItemSkillPill(skillName : String, modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(LocalStuddyColors.current.primary500)
            .padding(vertical = StuddyDimensions.pillTextPadding, horizontal = StuddyDimensions.pillTextPadding)
    ) {
        Text(
            text = skillName,
            color = LocalStuddyColors.current.primary900,
            style = StuddyTypography.pXSSmaller
        )
    }
}