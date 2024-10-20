package com.example.composelogin.ui.screens.homescreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun SwipeCard(
    onSwipeLeft: () -> Unit = {},
    onSwipeRight: () -> Unit = {},
    swipeThreshold: Float = 400f,
    sensitivityFactor: Float = 3f,
    content: @Composable () -> Unit
) {
    var offset by remember { mutableStateOf(0f) }
    var dismissRight by remember { mutableStateOf(false) }
    var dismissLeft by remember { mutableStateOf(false) }
    val density = LocalDensity.current.density

    LaunchedEffect(dismissRight) {
        if (dismissRight) {
            delay(300)
            onSwipeRight.invoke()
            dismissRight = false
        }
    }

    LaunchedEffect(dismissLeft) {
        if (dismissLeft) {
            delay(300)
            onSwipeLeft.invoke()
            dismissLeft = false
        }
    }

    Box(modifier = Modifier
        .offset { IntOffset(offset.roundToInt(), 0) }
        .pointerInput(Unit) {
            detectHorizontalDragGestures(onDragEnd = {
                offset = 0f
            }) { change, dragAmount ->

                offset += (dragAmount / density) * sensitivityFactor
                when {
                    offset > swipeThreshold -> {
                        dismissRight = true
                    }

                    offset < -swipeThreshold -> {
                        dismissLeft = true
                    }
                }
                if (change.positionChange() != Offset.Zero) change.consume()
            }
        }
        .graphicsLayer(
            alpha = 10f - animateFloatAsState(if (dismissRight) 1f else 0f, label = "").value,
            rotationZ = animateFloatAsState(offset / 50, label = "").value
        )) {
        content()
    }
}

@Composable
fun UserProfileCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Scrollable content inside a Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center) // Center the scrollable card in the parent
                .height(250.dp), // Set height for the scrollable card
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF1F1F1)
            )
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()) // Make this part scrollable
                    .padding(top = 50.dp) // Leave space for the fixed header
            ) {
                // List of items that will be scrollable
                for (i in 1..10) {
                    Text(
                        text = "Scrollable Item $i",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    HorizontalDivider()
                }
            }
        }

        // Fixed header positioned on top of the scrollable content
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter) // Align this box at the top center
                .background(Color(0xFF6200EE))
                .padding(16.dp)
        ) {
            Text(
                text = "Fixed Header",
                color = Color.White
            )
        }
    }
}
