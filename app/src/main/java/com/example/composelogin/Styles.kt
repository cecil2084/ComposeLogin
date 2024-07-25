package com.example.composelogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.ui.theme.quicksandFamily

@Composable
fun StuddyLogoStartUpScreen() {
    Image(
        painter = painterResource(id = R.drawable.studdylogo_with_text_bitmap),
        contentDescription = null,
        modifier = Modifier
            .padding(vertical = 72.dp)
            .width(296.dp)
            .height(102.dp)
            .border(width = 1.dp, color = LocalStuddyColors.current.primary700)
    )
}

@Composable
fun StuddyButtonBlue(
    content: String,
    onClick: () -> Unit,
    width: Dp = 265.dp
) {
    Button(
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

@Composable
fun StuddyButtonWhite(
    content: String,
    onClick: () -> Unit,
    width: Dp = 265.dp
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(width),
        contentPadding = PaddingValues(vertical = 12.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonColors(
            containerColor = Color.White,
            disabledContainerColor = LocalStuddyColors.current.primary500,
            contentColor = LocalStuddyColors.current.primary700,
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

@Composable
fun StuddyTextFieldGray(
    label: String,
    placeholder: String,
    textValue: String = "",
    isPassword: Boolean = false
) {
    var text by remember { mutableStateOf(textValue) }
    var passwordVisible by remember { mutableStateOf((false)) }
    var isFocused by remember {
        mutableStateOf(false)
    }

    val paddingVertical: Dp = 12.dp
    val paddingHorizontal: Dp = 20.dp
    val inputFieldAdditionalPaddingPassword: Dp = if (isPassword) 24.dp else 0.dp
    val borderColor: Color =
        if (!isFocused) LocalStuddyColors.current.lightNeutral600 else LocalStuddyColors.current.primary700
    val strokeWidth: Float = with(LocalDensity.current) { 1.dp.toPx() }
    val borderRadius: Float = with(LocalDensity.current) { 20.dp.toPx() }
    val leftCutPosition: Float = with(LocalDensity.current) { 32.dp.toPx() }
    val labelCutoutPadding: Float = with(LocalDensity.current) { 6.dp.toPx() }
    val labelColor: Color =
        if (!isFocused) LocalStuddyColors.current.lightNeutral600 else LocalStuddyColors.current.primary700
    val labelTextSize: TextUnit = 10.sp
    val labelTextSizeDp: Dp = with(LocalDensity.current) { labelTextSize.toDp() }
    val labelStyle = TextStyle(
        fontSize = 10.sp,
        fontFamily = quicksandFamily,
        fontWeight = FontWeight.Medium,
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
                val eyeconColor: Color =
                    if (!isFocused) LocalStuddyColors.current.lightNeutral600 else LocalStuddyColors.current.primary800

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
                value = text,
                onValueChange = {
                    text = it
                },
                onTextLayout = { },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = LocalStuddyColors.current.lightNeutral600,
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
                    if (text.isEmpty()) {
                        BasicText(
                            text = placeholder,
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = LocalStuddyColors.current.lightNeutral800,
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

@Composable
fun StuddyOrangeToggleSwitch(
    isChecked: Boolean,
){

}

class StuddyPasswordVisualTransformation(
    private val mask: Char = '*'
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val maskedString = AnnotatedString(
            mask.toString().repeat(text.length), spanStyle = SpanStyle(
                fontFamily = quicksandFamily
            )
        )
        return TransformedText(maskedString, OffsetMapping.Identity)
    }
}