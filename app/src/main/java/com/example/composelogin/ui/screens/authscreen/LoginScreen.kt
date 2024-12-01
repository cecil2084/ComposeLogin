package com.example.composelogin.ui.screens.authscreen

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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.stringResource
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
import com.example.composelogin.ui.screens.styles.StuddyToggleButton
import com.example.composelogin.ui.states.LoginState
import com.example.composelogin.ui.states.LoginUiState
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.theme.StuddyTypography
import com.example.composelogin.ui.theme.fredokaFamily
import com.example.composelogin.ui.theme.quicksandFamily

@Composable
fun MainLoginScreen(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
    uiState: LoginUiState,
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
        LoginContainer(
            email,
            password,
            onEmailChange,
            onPasswordChange,
            onSignUpClick,
            onLoginClick,
            uiState
        )
    }
}

@Composable
fun LoginContainer(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
    uiState: LoginUiState,
) {

    var isError by remember { mutableStateOf(false) }
    var loginLabel by remember { mutableStateOf("Log in") }
    var isLoading by remember { mutableStateOf(false) }


    when (uiState) {
        is LoginUiState.Loading -> {
            loginLabel = "Loading..."
            isLoading = true
        }

        is LoginUiState.Error -> {
            loginLabel = "Log in"
            isError = true
            isLoading = false
        }

        is LoginUiState.Success -> {
            loginLabel = "Success!"
            isError = false
            isLoading = false
        }

        else -> {
            loginLabel = "Log in"
            isError = false
            isLoading = false
        }
    }

    val text: AnnotatedString = buildAnnotatedString {
        append(stringResource(R.string.new_to_studdy) + " ")
        pushStringAnnotation(tag = "click", annotation = "click")
        withStyle(
            SpanStyle(
                textDecoration = TextDecoration.Underline,
            )
        ) {
            append(stringResource(R.string.signup))
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
            text = stringResource(R.string.login),
            fontSize = 32.sp,
            color = LocalStuddyColors.current.lightNeutral600,
            textAlign = TextAlign.Center
        )

        // Input Fields for Sign Up
        StuddyTextFieldGray(
            value = email,
            onValueChange = onEmailChange,
            label = stringResource(R.string.email_label),
            placeholder = "johnny_AppleSeed",
            isError = isError,
        )
        StuddyTextFieldGray(
            value = password,
            onValueChange = onPasswordChange,
            label = stringResource(R.string.password_label),
            isPassword = true,
            isError = isError,
        )

        if (isError) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Invalid Username and Password",
                    color = LocalStuddyColors.current.error700,
                    style = StuddyTypography.pXXS
                )
            }
        }

        Row(
            modifier = Modifier.width(265.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
//                StuddyToggleButton(
//                    enabled = true,
//                    checked = rememberMe,
//                    onClick = onRememberMeChange)
//                Text(
//                    text = stringResource(R.string.remember_me),
//                    fontSize = 12.sp,
//                    fontFamily = quicksandFamily,
//                    fontWeight = FontWeight.Medium,
//                    color = LocalStuddyColors.current.lightNeutral600
//                )
            }
            Text(
                text = stringResource(R.string.forgot_password),
                fontSize = 12.sp,
                fontFamily = quicksandFamily,
                fontWeight = FontWeight.Medium,
                color = LocalStuddyColors.current.primary700
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        if (!isLoading)
            StuddyButtonBlue(
//            content = stringResource(R.string.login),
                content = loginLabel,
                onClick = onLoginClick
            )
        else
            CircularProgressIndicator()

        Text(
            text = stringResource(R.string.or_login_using),
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

        // dont have an account clickable
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
