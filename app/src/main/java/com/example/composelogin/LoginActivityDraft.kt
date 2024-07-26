package com.example.composelogin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.ui.theme.ComposeLoginTheme
import com.example.composelogin.ui.theme.fredokaFamily
import com.example.composelogin.ui.theme.quicksandFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class LoginActivityDraft : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLoginTheme {
                MainLoginScreen()
            }
        }
    }
}

@Composable
fun MainLoginScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = LocalStuddyColors.current.primary700)
            .statusBarsPadding()
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) { StuddyLogoStartUpScreen() }
        LoginContainer()
    }
}

@Composable
fun LoginContainer() {
    var checked by remember { mutableStateOf(false) }
    val text: AnnotatedString = buildAnnotatedString {
        append("new to Studdy? ")
        pushStringAnnotation(tag = "click", annotation = "click")
        withStyle(
            SpanStyle(
                textDecoration = TextDecoration.Underline,
            )
        ) {
            append("Sign Up")
        }
        pop()
    }
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .background(
                Color.White,
                shape = RoundedCornerShape(topStart = 58.dp, topEnd = 58.dp)
            )
            .padding(vertical = 72.dp)
            .fillMaxWidth()
//            .verticalScroll(rememberScrollState())
            .navigationBarsPadding()

    ) {
        // Sign Up large text
        Text(
            fontFamily = fredokaFamily,
            fontWeight = FontWeight.Medium,
            text = "Log In",
            fontSize = 32.sp,
            color = LocalStuddyColors.current.lightNeutral600,
            textAlign = TextAlign.Center
        )

        // Input Fields for Sign Up
        StuddyTextFieldGray("Email Address", "Type here")
        StuddyTextFieldGray("Password", "Type here", isPassword = true)

        Row(
            modifier = Modifier.width(265.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StuddyToggleButton(enabled = true, checked = checked, onClick = {})
                Text(
                    text = "Remember Me",
                    fontSize = 12.sp,
                    fontFamily = quicksandFamily,
                    fontWeight = FontWeight.Medium,
                    color = LocalStuddyColors.current.lightNeutral600
                )
            }
            Text(
                text = "Forgot Password?",
                fontSize = 12.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Medium,
                color = LocalStuddyColors.current.primary700
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        StuddyButtonBlue(
            content = "Log in",
            onClick = { }
        )

//            Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "or login using",
            fontSize = 12.sp,
            fontFamily = quicksandFamily,
            fontWeight = FontWeight.Medium,
            color = LocalStuddyColors.current.lightNeutral700
        )

        // Other Sign Up Methods
        Row(
            modifier = Modifier.width(265.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            val iconButtonOuterSize: Dp = 48.dp
            val iconPadding: Dp = 8.dp
            val iconInnerSize: Dp = 30.dp
            IconButton(
                onClick = {}, modifier = Modifier
                    .size(iconButtonOuterSize)
                    .background(color = Color.White)
            ) {
                Surface(
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 2.dp,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "sign up with Google",
                        modifier = Modifier
                            .size(iconInnerSize + iconPadding)
                            .padding(iconPadding)
                    )
                }
            }

            IconButton(onClick = {}, modifier = Modifier.size(48.dp)) {
                Surface(
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 2.dp,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "sign up with Facebook",
                        modifier = Modifier
                            .size(iconInnerSize + iconPadding)
                            .padding(iconPadding)
                    )
                }
            }

            IconButton(onClick = {}, modifier = Modifier.size(48.dp)) {
                Surface(
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 2.dp,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_apple),
                        contentDescription = "sign up with Apple",
                        modifier = Modifier
                            .size(iconInnerSize + iconPadding)
                            .padding(iconPadding)
                    )
                }
            }
        }

        // dont have an account account clickable
        ClickableText(
            style = TextStyle(
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = LocalStuddyColors.current.lightNeutral600
            ),
            text = text,
            onClick = { offset ->
                text.getStringAnnotations(tag = "click", start = offset, end = offset)
                    .firstOrNull()
                    ?.let {
                        context.startActivity(Intent(context, MainActivity::class.java))
                    }
            }
        )
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