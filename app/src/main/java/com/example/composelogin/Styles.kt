package com.example.composelogin

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.ui.theme.quicksandFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

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

@Composable
fun StuddyButtonWhite(
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
    enabled: Boolean = true,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    isPassword: Boolean = false,
    isError: Boolean = false,
    readOnly: Boolean = false,
) {
    var passwordVisible by remember { mutableStateOf((false)) }
    var isFocused by remember {
        mutableStateOf(false)
    }

    val paddingVertical: Dp = 12.dp
    val paddingHorizontal: Dp = 20.dp
    val inputFieldAdditionalPaddingPassword: Dp = if (isPassword) 24.dp else 0.dp
    val borderColor: Color =
        if (!enabled) LocalStuddyColors.current.lightNeutral800 else if (isError) LocalStuddyColors.current.error700 else if (!isFocused) LocalStuddyColors.current.lightNeutral600 else LocalStuddyColors.current.primary700
    val strokeWidth: Float = with(LocalDensity.current) { 1.dp.toPx() }
    val borderRadius: Float = with(LocalDensity.current) { 20.dp.toPx() }
    val leftCutPosition: Float = with(LocalDensity.current) { 32.dp.toPx() }
    val labelCutoutPadding: Float = with(LocalDensity.current) { 6.dp.toPx() }
    val labelColor: Color = borderColor
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
                    if (!enabled) LocalStuddyColors.current.lightNeutral800 else if (isError) LocalStuddyColors.current.error700 else if (!isFocused) LocalStuddyColors.current.lightNeutral600 else LocalStuddyColors.current.primary800

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
                value = value,
                onValueChange = onValueChange,
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
                    if (value.isEmpty()) {
                        BasicText(
                            text = placeholder,
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = if (isError) LocalStuddyColors.current.error700 else LocalStuddyColors.current.lightNeutral800,
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

enum class DragAnchors {
    Start,
    End,
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StuddyToggleButton(
    enabled: Boolean = true,
    checked: Boolean = false,
    onClick: () -> Unit,
//    toggleOption: MutableState<Boolean>
) {
    val toggleHandleSize: Dp = 24.dp
    val toggleTrackRadius: Dp = 6.dp
    val toggleTrackWidth: Dp = 36.dp
    val toggleTrackHeight: Dp = 12.dp

    val toggleHandleColor: Color =
        if (enabled) LocalStuddyColors.current.warning700 else Color.White
    val toggleTrackColor: Color =
        if (enabled) LocalStuddyColors.current.lightNeutral600 else LocalStuddyColors.current.lightNeutral800
    val toggleTrackCheckedColor: Color =
        if (enabled) LocalStuddyColors.current.primary500 else LocalStuddyColors.current.lightNeutral700

    val velocityThreshold: Float = with(LocalDensity.current) { 125.dp.toPx() }
    val trackEnd: Float =
        with(LocalDensity.current) { (toggleTrackWidth - toggleHandleSize).toPx() }
    val trackColorEnd: Int =
        with(LocalDensity.current) {
            (toggleTrackWidth - toggleHandleSize + 5.dp).toPx().roundToInt()
        }

    val scope: CoroutineScope = rememberCoroutineScope()
    val state: AnchoredDraggableState<DragAnchors> = remember {
        AnchoredDraggableState(
            initialValue = if (checked) DragAnchors.End else DragAnchors.Start,
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { velocityThreshold },
            animationSpec = tween()
        ).apply {
            updateAnchors(
                DraggableAnchors {
                    DragAnchors.Start at 0f
                    DragAnchors.End at trackEnd
                }
            )
        }
    }

//    val trackColor: Color =
//        if (state.currentValue == DragAnchors.Start) LocalStuddyColors.current.darkNeutral600 else LocalStuddyColors.current.primary500

    Box(
        modifier = Modifier
            .then(
                if (enabled) {
                    Modifier.clickable {
                        scope.launch {
                            if (state.currentValue == DragAnchors.Start) {
                                state.animateTo(DragAnchors.End)
                            } else {
                                state.animateTo(DragAnchors.Start)
                            }
                        }
                    }
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        // unchecked track
        Box(
            modifier = Modifier
                .width(toggleTrackWidth)
                .height(toggleTrackHeight)
                .clip(shape = RoundedCornerShape(toggleTrackRadius))
                .background(toggleTrackColor)
        ) {
            // checked track
            Box(
                modifier = Modifier
                    .then(
                        if (enabled) {
                            Modifier
                                .offset {
                                    IntOffset(
                                        x = state
                                            .requireOffset()
                                            .roundToInt() - trackColorEnd,
                                        y = 0,
                                    )
                                }
                                .anchoredDraggable(
                                    state = state,
                                    orientation = Orientation.Horizontal
                                )
                        } else {
                            when (checked) {
                                true -> Modifier
                                false -> Modifier.offset(x = -toggleTrackWidth, y = 0.dp)
                            }
                        }
                    )
                    .width(toggleTrackWidth)
                    .height(toggleTrackHeight)
                    .background(toggleTrackCheckedColor)
            )
        }
        // toggle handle
        Box(
            modifier = Modifier
                .then(
                    if (enabled) {
                        Modifier
                            .offset {
                                IntOffset(
                                    x = state
                                        .requireOffset()
                                        .roundToInt(),
                                    y = 0,
                                )
                            }
                            .anchoredDraggable(
                                state = state,
                                orientation = Orientation.Horizontal
                            )
                    } else {
                        Modifier
                        when (checked) {
                            true -> Modifier
                                .offset(x = toggleTrackWidth - toggleHandleSize, y = 0.dp)
                                .border(
                                    border = BorderStroke(
                                        1.dp,
                                        LocalStuddyColors.current.lightNeutral700
                                    ),
                                    shape = CircleShape
                                )

                            false -> Modifier
                                .border(
                                    border = BorderStroke(
                                        1.dp,
                                        LocalStuddyColors.current.lightNeutral800
                                    ),
                                    shape = CircleShape
                                )
                        }
                    }
                )
                .size(toggleHandleSize)
                .clip(shape = CircleShape)
                .background(toggleHandleColor)
        )
    }
}