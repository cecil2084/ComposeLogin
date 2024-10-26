package com.example.composelogin.ui.screens.styles.dropdowns

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.ui.screens.styles.dimensions.StuddyDimensions
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.theme.quicksandFamily

//@Composable
//fun StuddyDropDownTextFieldSetUpTypable(){
//
//}

@Composable
fun StuddyDropDownMenuBeta(
    list: List<String>,
    selectedItem: String,
//    selectedIndex: Int,
    isFocused: Boolean,
    isExpanded: Boolean,
    enabled: Boolean = true,
    label: String,
    width: Dp = 265.dp,
    isError: Boolean = false,
    onDropDownClick: () -> Unit,
    onDismissRequest: () -> Unit,
    onSkillClick: () -> Unit
) {
    Box() {
        StuddyDropDownTextField(
            value = selectedItem,
            onClick = onDropDownClick,
//            onClick = {
//                isExpanded = true
//                isFocused = true
//            },
            label = label,
            enabled = enabled,
            isFocused = isFocused,
            width = width,
            isError = isError
        )
        if (enabled) {
            MaterialTheme(
                shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(StuddyDimensions.buttonBorderRadius)),
                colorScheme = MaterialTheme.colorScheme.copy(surface = Color.White)
            ) {
                DropdownMenu(
                    modifier = Modifier.width(width),
                    expanded = isExpanded,
                    onDismissRequest = onDismissRequest
//                    onDismissRequest = {
//                        isExpanded = false
//                        isFocused = false
//                    }
                ) {
                    list.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            text = {
                                Row {
                                    Text(
                                        item,
                                        fontSize = 14.sp,
                                        fontFamily = quicksandFamily,
//                                        color = if (selectedIndex == index) LocalStuddyColors.current.primary700 else LocalStuddyColors.current.lightNeutral600
                                    )
                                }
                            },
                            onClick = onSkillClick,
//                            onClick = {
//                                selectedItem = list[index]
//                                selectedIndex = index
//                                isExpanded = false
//                                isFocused = false
//                            },
                            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 20.dp)
                        )
                    }
                }
            }
        }
    }
}



