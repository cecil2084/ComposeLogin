package com.example.composelogin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.ui.theme.ComposeLoginTheme
import com.example.composelogin.ui.theme.fredokaFamily
import com.example.composelogin.ui.theme.quicksandFamily

class NewAccountProfileSetUp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLoginTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
                MainProfileDetailsSetUp()
            }
        }
    }
}

@Composable
fun MainProfileDetailsSetUp() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        Box(modifier = Modifier.weight(1f)) {
//            TODO("Text slideshow here")
        }

        ProfileDetailsMainInterface()
    }
}

@Composable
fun ProfileDetailsMainInterface() {
    // all the credentials
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val gender = remember { mutableStateOf("") }

    var currentPage by remember { mutableIntStateOf(1) }
    val pageTotal = 3
    val pagePadding: Dp = 8.dp
    val actualContentTopPadding: Dp = 24.dp - pagePadding
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                LocalStuddyColors.current.primary700,
                shape = RoundedCornerShape(topStart = 58.dp, topEnd = 58.dp)
            )
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 22.dp)
        ) {
            for (eachPage in 1..pageTotal) {
                Box(
                    modifier = Modifier
                        .width(20.dp)
                        .height(4.dp)
                        .clip(shape = RoundedCornerShape(4.dp))
                        .background(
                            when (currentPage == eachPage) {
                                true -> Color.White
                                false -> LocalStuddyColors.current.primary600
                            }
                        )
                )
            }
        }
        Spacer(modifier = Modifier.height(actualContentTopPadding))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            IconButton(modifier = Modifier.padding(start = 8.dp), onClick = {
                if (currentPage > 1) {
                    currentPage--
                } else {
                    context.startActivity(Intent(context, MainActivity::class.java))

                }
            }) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.back_button),
                    contentDescription = "back button",
                    tint = Color.White
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
//                    .background(Color.Magenta)
                    .defaultMinSize(minHeight = 48.dp)
            ) {
                Text(
                    fontFamily = fredokaFamily,
                    fontWeight = FontWeight.Medium,
                    text = "Profile Details",
                    fontSize = 32.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }

        when (currentPage) {
            1 -> PersonalDetailsStage(firstName, lastName, gender)
            2 -> UniversityEnrolled()
            3 -> UploadRegistrationForm()
        }


        StuddyButtonWhite(content = if (currentPage < pageTotal) "Next" else "Confirm", onClick = {
            if (currentPage < pageTotal) {
                currentPage++
            } else {
                context.startActivity(Intent(context, MainActivity::class.java))
            }
        })
    }
}

@Composable
//Contains F Name, L Name, Gender, and Birthdate
fun PersonalDetailsStage(
    firstName: MutableState<String>,
    lastName: MutableState<String>,
    gender: MutableState<String>
) {
    Box(
        modifier = Modifier.padding(bottom = 16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        //Dummy Picture Placeholder
        Box(
            modifier = Modifier
                .size(125.dp)
                .clip(shape = CircleShape)
                .border(
                    BorderStroke(1.dp, Color.White),
                    CircleShape
                )
        )

        IconButton(
            onClick = {},
            modifier = Modifier
                .offset(y = 10.dp)
                .size(43.dp)
                .background(LocalStuddyColors.current.primary700)
                .clip(shape = CircleShape)
                .border(
                    BorderStroke(2.dp, Color.White),
                    CircleShape
                ),
        ) {
            Icon(
                modifier = Modifier
                    .size(20.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.change_profile_picture_camera),
                contentDescription = "add/change profile picture",
                tint = Color.White
            )
        }

//        Box(
//            modifier = Modifier
//                .offset(y=10.dp)
//                .size(43.dp)
//                .background(LocalStuddyColors.current.primary700)
//                .clip(shape = CircleShape)
//                .border(
//                    BorderStroke(2.dp, Color.White),
//                    CircleShape
//                )
//        ) {
//
//        }
    }

    StuddyTextFieldWhite(
        value = firstName.value,
        onValueChange = { firstName.value = it },
        label = "First Name"
    )
    StuddyTextFieldWhite(
        value = lastName.value,
        onValueChange = { lastName.value = it },
        label = "Last Name"
    )
    StuddyTextFieldWhite(
        value = gender.value,
        onValueChange = { gender.value = it },
        label = "Gender"
    ) // DROPDOWN DAPAT TO BE CORRECTED
    StuddyButtonWhite(enabled = false, content = "Choose Birthday Date", onClick = { /*TODO*/ })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Contains School
fun UniversityEnrolled() {

    var anchorCoordinates by remember { mutableStateOf<LayoutCoordinates?>(null) }

    var list = listOf(
        "University of the East",
        "University one",
        "university two",
        "University three",
        "university 4",
        "University of the East",
        "University one",
        "university two",
        "University three",
        "university 4",
        "University of the East",
        "University one",
        "university two",
        "University three",
        "university 4"
    )
    var selectedItem by remember {
        mutableStateOf("Select University")
    }
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = !isExpanded }) {
//        MaterialTheme(shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(20.dp))){
//            TextField(
//                modifier = Modifier.menuAnchor(),
//                value = selectedItem,
//                onValueChange = {},
//                readOnly = true,
//                shape = RoundedCornerShape(20.dp)
//            )
//        }
        StuddyDropDownTextField(value = selectedItem, onValueChange = {}, label = "School")
        MaterialTheme(
            shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(20.dp)),
            colorScheme = MaterialTheme.colorScheme.copy(surface = Color.White)
        ) {
            ExposedDropdownMenu(
                modifier = Modifier,
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }) {
                list.forEachIndexed { index, item ->
                    DropdownMenuItem(text = {
                        Row {
                            Text(
                                item,
                                fontSize = 14.sp,
                                fontFamily = quicksandFamily,
                                color = if (selectedIndex == index) LocalStuddyColors.current.primary700 else LocalStuddyColors.current.lightNeutral600
                            )
                        }
                    }, onClick = {
                        selectedItem = list[index]
                        selectedIndex = index
                        isExpanded = false
                    },
                        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 20.dp)
                    )
                }
            }
        }
    }
}

@Composable
//Contains Registration form upload
fun UploadRegistrationForm() {

}
