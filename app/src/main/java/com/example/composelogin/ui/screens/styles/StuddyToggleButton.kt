package com.example.composelogin.ui.screens.styles

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.composelogin.ui.enums.DragAnchors
import com.example.composelogin.ui.theme.LocalStuddyColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

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