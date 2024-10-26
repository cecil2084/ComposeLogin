package com.example.composelogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.border
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.ui.theme.ComposeLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLoginTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    // Header Text
                    Text(
                        text = "Wants to study with you",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF2EABF1))
                            .padding(16.dp),
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    // Card Grid
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        val profiles = listOf(
                            ProfileData("Racy Anne Dumable", "Computer Science", R.drawable.racy),
                            ProfileData("Jamie Doe", "Biology", R.drawable.jamie),
                            ProfileData("JM Smith", "Engineering", R.drawable.jm),
                            ProfileData("Kyla Ray", "Physics", R.drawable.kyla),
                            ProfileData("Zoe Green", "Mathematics", R.drawable.zoe),
                            ProfileData("Adrian Black", "Chemistry", R.drawable.adrian),
                            ProfileData("Lawrence White", "Art History", R.drawable.lawrence)
                        )

                        items(20) { index ->
                            val profile = profiles[index % profiles.size]
                            PeopleCard(
                                painter = painterResource(id = profile.imageRes),
                                contentDescription = "Profile Picture",
                                name = profile.name,
                                course = profile.course,
                                medals = listOf(2, 5),
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

data class ProfileData(val name: String, val course: String, val imageRes: Int)

@Composable
fun PeopleCard(
    painter: Painter,
    contentDescription: String,
    name: String,
    course: String,
    medals: List<Int>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(2.dp, Color(0xFF2EABF1), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box {
            Column {
                // Profile Image Filling the Card without Aspect Ratio
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                )

                // Thick Bottom Border with Name and Course at the Very Bottom
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF2EABF1))
                        .padding(8.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column {
                        Text(
                            text = name,
                            style = TextStyle(color = Color.White, fontSize = 16.sp)
                        )
                        Text(
                            text = course,
                            style = TextStyle(color = Color.White, fontSize = 12.sp)
                        )
                    }
                }
            }

            // Medals Row at the Top Left Corner
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopStart)
            ) {
                medals.forEach { count ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFFF9D14))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_medal),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = count.toString(), color = Color.White, fontSize = 10.sp)
                    }
                }
            }
        }
    }
}

//package com.example.composelogin
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.composelogin.ui.theme.ComposeLoginTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            ComposeLoginTheme {
//                Column(modifier = Modifier.fillMaxSize()) {
//                    // Header Text
//                    Text(
//                        text = "Wants to study with you",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(Color(0xFF2EABF1))
//                            .padding(16.dp),
//                        color = Color.White,
//                        fontSize = 20.sp,
//                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
//                    )
//
//                    // Card Grid
//                    LazyVerticalGrid(
//                        columns = GridCells.Fixed(2),
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(8.dp)
//                    ) {
//                        val namesAndCourses = listOf(
//                            "Racy Anne Dumable" to "Computer Science",
//                            "John Doe" to "Engineering",
//                            "Jane Smith" to "Mathematics",
//                            "Alice Johnson" to "Physics",
//                            "Robert Brown" to "Chemistry"
//                        )
//
//                        items(20) { index ->
//                            val (name, course) = namesAndCourses[index % namesAndCourses.size]
//                            PeopleCard(
//                                painter = painterResource(id = R.drawable.racy),
//                                contentDescription = "Profile Picture",
//                                name = name,
//                                course = course,
//                                medals = listOf(2, 5),
//                                modifier = Modifier.padding(8.dp)
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun PeopleCard(
//    painter: Painter,
//    contentDescription: String,
//    name: String,
//    course: String,
//    medals: List<Int>,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier
//            .fillMaxWidth()
//            .border(2.dp, Color(0xFF2EABF1), RoundedCornerShape(16.dp)),
//        shape = RoundedCornerShape(16.dp),
//    ) {
//        Box {
//            Column {
//                // Profile Image Filling the Card without Aspect Ratio
//                Image(
//                    painter = painter,
//                    contentDescription = contentDescription,
//                    contentScale = ContentScale.Crop, // Crops image to fill space proportionally
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(150.dp)
//                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
//                )
//
//                // Thick Bottom Border with Name and Course at the Very Bottom
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(Color(0xFF2EABF1))
//                        .padding(8.dp), // Padding inside the bottom box
//                    contentAlignment = Alignment.CenterStart
//                ) {
//                    Column {
//                        Text(
//                            text = name,
//                            style = TextStyle(color = Color.White, fontSize = 16.sp)
//                        )
//                        Text(
//                            text = course,
//                            style = TextStyle(color = Color.White, fontSize = 12.sp)
//                        )
//                    }
//                }
//            }
//
//            // Medals Row at the Top Left Corner
//            Row(
//                modifier = Modifier
//                    .padding(8.dp)
//                    .align(Alignment.TopStart)
//            ) {
//                medals.forEach { count ->
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier
//                            .padding(end = 4.dp)
//                            .clip(RoundedCornerShape(8.dp))
//                            .background(Color(0xFFFF9D14))
//                            .padding(horizontal = 6.dp, vertical = 2.dp)
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.ic_medal),
//                            contentDescription = null,
//                            tint = Color.White,
//                            modifier = Modifier.size(12.dp)
//                        )
//                        Spacer(modifier = Modifier.width(4.dp))
//                        Text(text = count.toString(), color = Color.White, fontSize = 10.sp)
//                    }
//                }
//            }
//        }
//    }
//}


//package com.example.composelogin
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.composelogin.ui.theme.ComposeLoginTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            ComposeLoginTheme {
//                val painter = painterResource(id = R.drawable.racy)
//
//                // Display grid with two cards per row and 10 rows
//                LazyVerticalGrid(
//                    columns = GridCells.Fixed(2),
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(8.dp)
//                ) {
//                    items(20) { // 2 cards per row, 10 rows = 20 items
//                        PeopleCard(
//                            painter = painter,
//                            contentDescription = "Profile Picture",
//                            title = "Racy Anne Dumable",
//                            modifier = Modifier.padding(8.dp)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun PeopleCard(
//    painter: Painter,
//    contentDescription: String,
//    title: String,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier
//            .fillMaxWidth()
//            .border(2.dp, Color(0xFF2EABF1), RoundedCornerShape(16.dp)),
//        shape = RoundedCornerShape(16.dp),
//    ) {
//        Column {
//            Box(modifier = Modifier.height(150.dp)) {
//                Image(
//                    painter = painter,
//                    contentDescription = contentDescription,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.fillMaxSize()
//                )
//            }
//
//            // Thicker Bottom Border with Label Text
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(40.dp)
//                    .background(Color(0xFF2EABF1)),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = title,
//                    style = TextStyle(color = Color.White, fontSize = 16.sp)
//                )
//            }
//        }
//    }
//}

//package com.example.composelogin
//
//import android.os.Bundle
//import android.provider.Contacts.People
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.composelogin.ui.screens.MainLoginScreen
//import com.example.composelogin.ui.screens.MainProfileDetailsSetUp
//import com.example.composelogin.ui.screens.MainSignUpScreen
//import com.example.composelogin.ui.screens.PeopleCard
//import com.example.composelogin.ui.theme.ComposeLoginTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            ComposeLoginTheme {
//                val navController = rememberNavController()
//                NavHost(navController = navController, startDestination = NavRoutes.USERS_WANTED) {
//                    composable(NavRoutes.SIGNUP) {
//                        MainSignUpScreen(
//                            onSignUpClick = { navController.navigate(NavRoutes.SETUP_PROFILE) },
//                            onLoginClick = { navController.navigate(NavRoutes.LOGIN) }
//                        )
//                    }
//                    composable(NavRoutes.LOGIN) {
//                        MainLoginScreen(
//                            onSignUpClick = { navController.navigate(NavRoutes.SIGNUP) }
//                        )
//                    }
//                    composable(NavRoutes.SETUP_PROFILE) {
//                        MainProfileDetailsSetUp(
//                            onConfirmClick = { navController.navigate(NavRoutes.LOGIN) },
//                            onSignUpClick = { navController.navigate(NavRoutes.SIGNUP) }
//                        )
//                    }
//                    composable(NavRoutes.USERS_WANTED) {
//                        val painter = painterResource(id = R.drawable.racy)
//                        val description = "Racy"
//                        val title = "Racy Anne Dumable"
//
//                        Box(modifier = Modifier
//                            .fillMaxSize()
//                        )
//                        Box(modifier=Modifier
//                            .fillMaxWidth(0.5f)
//                            .padding(16.dp)
//                            .border(5.dp, Color(0xFF2EABF1), RoundedCornerShape(16.dp))
//
//                        ) {
//                            PeopleCard(
//                                painter = painter,
//                                contentDescription = description,
//                                title = title
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//
//@Composable
//fun PeopleCard(
//    painter: Painter,
//    contentDescription: String,
//    title: String,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier.fillMaxWidth(),
//        shape = RoundedCornerShape(15.dp),
//    ) {
//        Box(modifier = Modifier.height(200.dp)) {
//            Image(
//                painter = painter,
//                contentDescription = contentDescription,
//                contentScale = ContentScale.Crop
//            )
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(12.dp),
//                contentAlignment = Alignment.BottomStart
//            ) {
//                Text(title, style = TextStyle(color = Color.White, fontSize = 16.sp))
//            }
//        }
//    }
//}
