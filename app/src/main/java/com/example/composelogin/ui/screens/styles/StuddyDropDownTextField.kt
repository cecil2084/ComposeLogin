package com.example.composelogin.ui.screens.styles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.theme.quicksandFamily

@Composable
private fun StuddyDropDownTextField(
    isFocused: Boolean,
    value: String,
    onClick: () -> Unit,
    label: String,
    enabled: Boolean,
    width: Dp = 265.dp,
    isError: Boolean = false
) {
    val paddingVertical: Dp = 12.dp
    val paddingHorizontal: Dp = 20.dp
    val borderColor: Color =
        if (!enabled) LocalStuddyColors.current.primary500 else if (isError) LocalStuddyColors.current.error700 else Color.White
    val strokeWidth: Float =
        with(LocalDensity.current) { if (!isFocused) 1.dp.toPx() else 2.dp.toPx() }
    val borderRadius: Float = with(LocalDensity.current) { 20.dp.toPx() }
    val leftCutPosition: Float = with(LocalDensity.current) { 32.dp.toPx() }
    val selectionTextWeight = if (isFocused) FontWeight.Bold else FontWeight.Medium
    val labelCutoutPadding: Float = with(LocalDensity.current) { 6.dp.toPx() }
    val labelColor: Color = borderColor
    val labelTextSize: TextUnit = 10.sp
    val labelTextSizeDp: Dp = with(LocalDensity.current) { labelTextSize.toDp() }
    val labelFontWeight: FontWeight = if (!isFocused) FontWeight.Medium else FontWeight.Bold
    val labelStyle = TextStyle(
        fontSize = labelTextSize,
        fontFamily = quicksandFamily,
        fontWeight = labelFontWeight,
        color = labelColor
    )
    val labelTextWidth: Float = with(LocalDensity.current) {
        rememberTextMeasurer().measure(
            label,
            labelStyle
        ).size.width.toDp().toPx()
    }

    BasicText(
        text = label,
        style = labelStyle,
        modifier = Modifier.offset(32.dp, 0.dp)
    )

    Box(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(top = labelTextSizeDp / 2)
            .width(width)
            .drawBehind {
                clipRect(
                    top = 0f,
                    left = leftCutPosition - labelCutoutPadding,
                    bottom = strokeWidth * 2,
                    right = leftCutPosition + labelCutoutPadding + labelTextWidth,
                    clipOp = ClipOp.Difference
                ) {
                    drawRoundRect(
                        color = borderColor,
                        topLeft = Offset(strokeWidth, strokeWidth),
                        size = Size(
                            size.width - strokeWidth * 2,
                            size.height - strokeWidth * 2
                        ),
                        cornerRadius = CornerRadius(borderRadius, borderRadius),
                        style = Stroke(width = strokeWidth)
                    )
                }
            }
            .padding(vertical = paddingVertical, horizontal = paddingHorizontal)


    ) {
        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = value,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = quicksandFamily,
                color = borderColor,
                fontWeight = selectionTextWeight
            )
        )
    }
}

@Composable
fun StuddyDropDownMenu(
    list: List<String>,
    selectedItem: MutableState<String>,
    selectedIndex: MutableState<Int>,
    isFocused: MutableState<Boolean>,
    isExpanded: MutableState<Boolean>,
    enabled: Boolean = true,
    label: String,
    width: Dp = 265.dp,
    isError: Boolean = false
) {
    Box() {
        StuddyDropDownTextField(
            value = selectedItem.value,
            onClick = {
                isExpanded.value = true
                isFocused.value = true
            },
            label = label,
            enabled = enabled,
            isFocused = isFocused.value,
            width = width,
            isError = isError
        )
        if (enabled) {
            MaterialTheme(
                shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(20.dp)),
                colorScheme = MaterialTheme.colorScheme.copy(surface = Color.White)
            ) {
                DropdownMenu(
                    modifier = Modifier.width(width),
                    expanded = isExpanded.value,
                    onDismissRequest = {
                        isExpanded.value = false
                        isFocused.value = false
                    }
                ) {
                    list.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            text = {
                                Row {
                                    Text(
                                        item,
                                        fontSize = 14.sp,
                                        fontFamily = quicksandFamily,
                                        color = if (selectedIndex.value == index) LocalStuddyColors.current.primary700 else LocalStuddyColors.current.lightNeutral600
                                    )
                                }
                            }, onClick = {
                                selectedItem.value = list[index]
                                selectedIndex.value = index
                                isExpanded.value = false
                                isFocused.value = false
                            },
                            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 20.dp)
                        )
                    }
                }
            }
        }

    }
}