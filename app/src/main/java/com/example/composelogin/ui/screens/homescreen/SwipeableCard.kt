package com.example.composelogin.ui.screens.homescreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.composelogin.R
import com.example.composelogin.model.UserProfileSwipeDetails
import com.example.composelogin.ui.screens.styles.dimensions.StuddyDimensions
import com.example.composelogin.ui.theme.StuddyTypography
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
    var offset by remember { mutableFloatStateOf(0f) }
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UserProfileCard(modifier: Modifier = Modifier, userProfileCard: UserProfileSwipeDetails) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White)

        ) {
            Image(
                painter = painterResource(id = userProfileCard.profilePicture),
                contentDescription = "racy user profile picture",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(StuddyDimensions.cardAspectRatio)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = StuddyDimensions.cardRoundedRadius,
                            bottomEnd = StuddyDimensions.cardRoundedRadius
                        )
                    ),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(StuddyDimensions.cardPadding)
            ) {
                Text(
                    text = userProfileCard.name,
                    color = Color.Black,
                    style = StuddyTypography.h4
                )

                Text(
                    text = userProfileCard.degreeProgram,
                    color = Color.Black,
                    style = StuddyTypography.pXS
                )

                Text(
                    text = userProfileCard.school,
                    color = Color.Black,
                    style = StuddyTypography.pXS
                )

                Spacer(modifier = Modifier.height(StuddyDimensions.cardSectionSpacing))

                Text(
                    text = stringResource(id = R.string.about),
                    color = Color.Black,
                    style = StuddyTypography.pXSBold
                )

                Text(
                    text = userProfileCard.about,
                    color = Color.Black,
                    style = StuddyTypography.pXS
                )

                Spacer(modifier = Modifier.height(StuddyDimensions.cardSectionSpacing))

                Text(
                    text = stringResource(id = R.string.academic_skills),
                    color = Color.Black,
                    style = StuddyTypography.h4
                )

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(StuddyDimensions.pillsSpacing),
                    verticalArrangement = Arrangement.spacedBy(StuddyDimensions.pillsSpacing)
                ) {
                    userProfileCard.academicSkills.forEach {
                        ItemSkillPill(it)
                    }
                }

                Spacer(modifier = Modifier.height(StuddyDimensions.cardSectionSpacing))

                Text(
                    text = stringResource(id = R.string.other_skills),
                    color = Color.Black,
                    style = StuddyTypography.h4
                )

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(StuddyDimensions.pillsSpacing),
                    verticalArrangement = Arrangement.spacedBy(StuddyDimensions.pillsSpacing)
                ) {
                    userProfileCard.otherSkills.forEach {
                        ItemSkillPill(it)
                    }
                }
            }

            Spacer(modifier = Modifier.height(100.dp))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0XFF1B638B).copy(alpha = 0.8f))
                .align(Alignment.BottomCenter)
                .padding(StuddyDimensions.cardPadding),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column {
                Text(
                    text = userProfileCard.name,
                    style = StuddyTypography.h5,
                    color = Color.White,
                )

                Text(
                    text = userProfileCard.age,
                    color = Color.White,
                    style = StuddyTypography.pSM
                )
            }
            Row {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.accept_user_btn),
                        contentDescription = "Accept Profile",
                        modifier = Modifier.size(StuddyDimensions.iconMedium),
                        tint = Color.Unspecified
                    )
                }

                IconButton(onClick = {}) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.reject_user_btn),
                        contentDescription = "Reject Profile",
                        modifier = Modifier.size(StuddyDimensions.iconMedium),
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
}