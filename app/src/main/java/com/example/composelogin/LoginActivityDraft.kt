package com.example.composelogin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.ui.theme.ComposeLoginTheme
import com.example.composelogin.ui.theme.fredokaFamily
import com.example.composelogin.ui.theme.quicksandFamily

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
        StuddyLogoStartUpScreen()
        LoginContainer()
    }
}

@Composable
fun LoginContainer() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 58.dp, topEnd = 58.dp)
                )
                .padding(vertical = 72.dp)
                .padding(bottom = 72.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
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
                    studdyToggleButton()

                    Text(
                        text = "Remember Me",
                        fontSize = 11.sp,
                        fontFamily = quicksandFamily,
                        fontWeight = FontWeight.Medium,
                        color = LocalStuddyColors.current.lightNeutral600
                    )
                }
                Text(
                    text = "Forgot Password?",
                    fontSize = 11.sp,
                    fontFamily = quicksandFamily,
                    fontWeight = FontWeight.Medium,
                    color = LocalStuddyColors.current.primary700
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            val context = LocalContext.current
            StuddyButtonBlue(
                content = "Log in",
                onClick = {
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

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
        }
    }
}

@Composable
fun studdyToggleButton() {
    val toggleHandleRadius: Dp = 7.dp
    val toggleTrackRadius: Dp = 6.dp
    val toggleTrackWidth: Dp = 25.dp
    val toggleTrackHeight: Dp = 9.dp

    Box(
        modifier = Modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .width(toggleTrackWidth)
                .height(toggleTrackHeight)
                .clip(shape= RoundedCornerShape(toggleTrackRadius))
                .background(LocalStuddyColors.current.primary500)
        )
        Box (
            modifier = Modifier
                .size(toggleHandleRadius*2)
                .clip(shape= CircleShape)
                .background(LocalStuddyColors.current.warning700)
        )
    }

}