package com.example.composelogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.ui.theme.fredokaFamily
import com.example.composelogin.ui.theme.quicksandFamily

@Composable
fun MainLoginScreen(
    onSignUpClick: () -> Unit
) {
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
        LoginContainer(onSignUpClick)
    }
}

@Composable
fun LoginContainer(
    onSignUpClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
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
        StuddyTextFieldGray(
            value = email,
            onValueChange = { email = it },
            label = "Email Address",
            placeholder = "johnappleseed@apple.com"
        )
        StuddyTextFieldGray(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            isPassword = true
        )

        Row(
            modifier = Modifier.width(265.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StuddyToggleButton(
                    enabled = true,
                    checked = rememberMe,
                    onClick = { rememberMe = !rememberMe })
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
                        onSignUpClick()
                        //GOTO SIGN UP SCREEN
                    }
            }
        )
    }
}
