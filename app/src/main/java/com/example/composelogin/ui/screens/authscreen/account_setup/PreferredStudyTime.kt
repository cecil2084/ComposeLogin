package com.example.composelogin.ui.screens.authscreen.account_setup

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.ui.states.SelectionState
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.theme.StuddyTypography
import com.example.composelogin.ui.theme.fredokaFamily

@Composable
fun PreferredStudyTimeScreen(
    choiceList: List<SelectionState>,
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    @StringRes description: Int,
    onSelectionChange: (SelectionState) -> Unit
) {
    Column(
        modifier = modifier
            .background(Color.Transparent)
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = stringResource(title),
            fontFamily = fredokaFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            fontSize = 20.sp,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(description),
            style = StuddyTypography.pXS,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(20.dp))

        choiceList.forEach { choice ->
            ToggleOptionButton(
                text = choice.selection.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(22.dp)
                    ),
                onClick = {
                    onSelectionChange(choice)
                },
                isSelected = choice.isSelected
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ToggleOptionButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(22.dp),
        contentPadding = PaddingValues(20.dp),
        onClick = onClick,
        modifier = modifier,
        colors = ButtonColors(
            containerColor = if (isSelected) Color.White else Color.Transparent,
            contentColor = if (isSelected) LocalStuddyColors.current.primary700 else Color.White,
            disabledContentColor = LocalStuddyColors.current.darkNeutral500,
            disabledContainerColor = LocalStuddyColors.current.darkNeutral500,
        )

    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text = text,
                style = StuddyTypography.pXS
            )
        }
    }
}