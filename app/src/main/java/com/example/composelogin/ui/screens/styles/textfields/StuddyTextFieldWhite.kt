package com.example.composelogin.ui.screens.styles.textfields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.R
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.screens.styles.StuddyPasswordVisualTransformation
import com.example.composelogin.ui.theme.quicksandFamily

@Composable
fun StuddyTextFieldWhite(
    enabled: Boolean = true,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    isPassword: Boolean = false,
    isError: Boolean = false,
    readOnly: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf((false)) }
    var isFocused by remember {
        mutableStateOf(false)
    }

    val paddingVertical: Dp = 12.dp
    val paddingHorizontal: Dp = 20.dp
    val inputFieldAdditionalPaddingPassword: Dp = if (isPassword) 24.dp else 0.dp

    val borderColor: Color =
        if (!enabled) LocalStuddyColors.current.primary500 else if (isError) LocalStuddyColors.current.error700 else Color.White
    val labelColor: Color = borderColor

    val strokeWidth: Float =
        with(LocalDensity.current) { if (!isFocused) 1.dp.toPx() else 2.dp.toPx() }
    val borderRadius: Float = with(LocalDensity.current) { 20.dp.toPx() }

    val leftCutPosition: Float =
        with(LocalDensity.current) { 32.dp.toPx() } //additional padding for the label
    val labelCutoutPadding: Float = with(LocalDensity.current) { 6.dp.toPx() }
    val labelTextSize: TextUnit = 10.sp
    val labelTextSizeDp: Dp = with(LocalDensity.current) { labelTextSize.toDp() }
    val labelFontWeight: FontWeight = if (!isFocused) FontWeight.Medium else FontWeight.Bold
    val labelStyle = TextStyle(
        fontSize = 10.sp,
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
    Box {
        BasicText(
            text = label,
            style = labelStyle,
            modifier = Modifier.offset(32.dp, 0.dp)
        )
        Box(modifier = Modifier.padding(top = labelTextSizeDp / 2)) {
            if (isPassword) {
                val passwordEyeVisible: ImageVector =
                    ImageVector.vectorResource(id = R.drawable.eyecon_hidden)
                val passwordEyeHidden: ImageVector =
                    ImageVector.vectorResource(id = R.drawable.eyecon_visible)
                val eyeconColor: Color = borderColor
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 10.dp)
                        .size(30.dp)
                ) {
                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        val passwordEyecon =
                            if (passwordVisible) passwordEyeHidden else passwordEyeVisible
                        Icon(
                            modifier = Modifier.width(20.dp),
                            imageVector = passwordEyecon,
                            contentDescription = "hide password",
                            tint = eyeconColor
                        )
                    }
                }
            }

            BasicTextField(
                enabled = enabled,
                readOnly = readOnly,
                cursorBrush = SolidColor(Color.White),
                value = value,
                onValueChange = onValueChange,
                onTextLayout = { },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = if (!enabled) LocalStuddyColors.current.primary500 else Color.White,
                    fontFamily = quicksandFamily,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
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
                    .width(265.dp)
                    .padding(vertical = paddingVertical, horizontal = paddingHorizontal)
                    .padding(end = inputFieldAdditionalPaddingPassword)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        BasicText(
                            text = placeholder,
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = if (isError) LocalStuddyColors.current.error700 else LocalStuddyColors.current.primary600,
                                fontFamily = quicksandFamily,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                    innerTextField()
                },
                singleLine = true,
                visualTransformation = if (!isPassword || passwordVisible) VisualTransformation.None else StuddyPasswordVisualTransformation(),
            )
        }
    }
}

