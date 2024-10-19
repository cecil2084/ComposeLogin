package com.example.composelogin.ui.screens

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
import com.example.composelogin.R
import com.example.composelogin.ui.screens.styles.buttons.StuddyButtonBlue
import com.example.composelogin.ui.screens.styles.StuddyLogoStartUpScreen
import com.example.composelogin.ui.screens.styles.textfields.StuddyTextFieldGray
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.theme.fredokaFamily
import com.example.composelogin.ui.theme.quicksandFamily

@Composable
fun MainSignUpScreen(
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit
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
        SignUpContainer(
            onSignUpClick,
            onLoginClick
        )
    }
}

@Composable
fun SignUpContainer(
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val text: AnnotatedString = buildAnnotatedString {
        append("already have an account? ")
        pushStringAnnotation(tag = "click", annotation = "click")
        withStyle(
            SpanStyle(
                textDecoration = TextDecoration.Underline,
            )
        ) {
            append("Login")
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
            .navigationBarsPadding()

    ) {
        // Sign Up large text
        Text(
            fontFamily = fredokaFamily,
            fontWeight = FontWeight.Medium,
            text = "Sign Up",
            fontSize = 32.sp,
            color = LocalStuddyColors.current.lightNeutral600,
            textAlign = TextAlign.Center
        )

        // Copyright statement
        Text(
            text = buildAnnotatedString {
                append("By signing up, you agree to our ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Medium,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("terms")
                }
                append(". Learn how we process in our ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Medium,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Privacy Policy")
                }
                append(" and ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Medium,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Cookies Policy")
                }
                append(".")
            },

            fontFamily = quicksandFamily,
            fontSize = 10.sp,
            color = LocalStuddyColors.current.lightNeutral600,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(266.dp)
        )

        // Input Fields for Sign Up
        StuddyTextFieldGray(
            value = username,
            onValueChange = { username = it },
            label = "Username",
            placeholder = "johnappleseed_2084"
        )
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

        Spacer(modifier = Modifier.height(16.dp))

        StuddyButtonBlue(
            content = "Sign Up",
            onClick = onSignUpClick
        )

        Text(
            text = "or sign up using",
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

        // already have an account clickable
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
                        onLoginClick()
                    }
            }
        )
    }
}