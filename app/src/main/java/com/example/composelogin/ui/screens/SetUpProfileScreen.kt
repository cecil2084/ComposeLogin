package com.example.composelogin.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.R
import com.example.composelogin.ui.screens.styles.buttons.StuddyButtonWhite
import com.example.composelogin.ui.screens.styles.StuddyDropDownMenu
import com.example.composelogin.ui.screens.styles.textfields.StuddyTextFieldWhite
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.theme.fredokaFamily

@Composable
fun MainProfileDetailsSetUp(
    onSignUpClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        Box(modifier = Modifier.weight(1f)) {
//            TODO("Text slideshow here")
        }

        ProfileDetailsMainInterface(onSignUpClick, onConfirmClick)
    }
}

@Composable
fun ProfileDetailsMainInterface(
    onSignUpClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    // all the credentials
    val firstName: MutableState<String> = remember { mutableStateOf("") }
    val lastName: MutableState<String> = remember { mutableStateOf("") }
    val gender: List<String> = listOf("Male", "Female", "Others")
    val selectedGender: MutableState<String> = remember { mutableStateOf("Select Gender") }
    val selectedGenderIndex: MutableState<Int> = remember { mutableIntStateOf(-1) }
    val isGenderFocused: MutableState<Boolean> = remember { mutableStateOf(false) }
    val isGenderExpanded: MutableState<Boolean> = remember { mutableStateOf(false) }

    // all the university list
    val universityList: List<String> = listOf(
        "Northeast Institute of Technology",
        "Pacific Crest University",
        "Highland State College",
        "Southern Ridge Institute",
        "Central Valley University",
        "Lakeside College of the Arts",
        "Emerald City University",
        "Redwood Institute of Science",
        "Coastal Bay University",
        "Mountainview Polytechnic",
        "Riverstone University",
        "Golden Plains Academy",
        "Horizon University",
        "Starlight Institute",
        "Bluegrass College",
        "Canyon State University",
        "Aspenwood University",
        "Everglades Institute",
        "Granite Hills College",
        "Meadowbrook University",
        "Suncrest Institute",
        "Pinehill University",
        "Maplewood College",
        "Bayside University",
        "Silverlake Institute",
        "Timberland University",
        "Coral Reef College",
        "Windriver Academy",
        "Cedar Grove University",
        "Ivy Ridge Institute",
        "Desert Sands University",
        "Willowbrook College",
        "Prairie State University",
        "Moonlit University",
        "Clearwater Institute",
        "Oakwood University",
        "Seaside College",
        "Snowcap Institute",
        "Golden Gate University",
        "Harborview University",
        "Wildflower Institute",
        "Stormpeak University",
        "Valley View College",
        "Stonebridge University",
        "Highlands University",
        "Crystal Lake College",
        "Sunflower State Institute",
        "Palmspring University",
        "Forest Glen University",
        "Riverbend Institute"
    )

    val selectedUniversityItem = remember {
        mutableStateOf("Select University")
    }
    val selectedUniversityIndex = remember {
        mutableIntStateOf(-1)
    }
    val isUniversityDropDownExpanded = remember {
        mutableStateOf(false)
    }
    val isUniversityDropDownFocused = remember {
        mutableStateOf(false)
    }

    val degreeProgramList = listOf(
        "Quantum Computing",
        "Environmental Robotics",
        "Cultural Cybernetics",
        "Biomedical Engineering",
        "Sustainable Architecture",
        "Digital Media Arts",
        "Astrobiology",
        "Marine Biotechnology",
        "Nanotechnology Engineering",
        "Renewable Energy Management",
        "Artificial Intelligence and Ethics",
        "Virtual Reality Design",
        "Forensic Anthropology",
        "Global Health Policy",
        "Cybersecurity Law",
        "Genomic Medicine",
        "Interactive Media Design",
        "Environmental Data Science",
        "Urban Planning and Smart Cities",
        "Space Sciences",
        "Advanced Culinary Arts",
        "Ecological Economics",
        "Sports Analytics",
        "Quantum Physics",
        "Marine Conservation",
        "Biomolecular Science",
        "Renewable Energy Engineering",
        "Film and Digital Storytelling",
        "Aerospace Engineering",
        "Global Development Studies",
        "Bioinformatics",
        "Cyberpsychology",
        "Environmental Policy and Management",
        "Genetic Counseling",
        "Human-Computer Interaction",
        "Intelligent Systems Engineering",
        "Neuroscience",
        "Pharmaceutical Sciences",
        "Robotics and Automation",
        "Sustainable Agriculture",
        "Urban Ecology",
        "Virtual Environments",
        "Wildlife Conservation",
        "Youth and Family Studies",
        "Zoology and Wildlife Science",
        "Ecotourism Management",
        "Game Design and Development",
        "Health Informatics",
        "Interior Architecture",
        "Mechatronics"
    )

    val selectedProgramDegree = remember {
        mutableStateOf("Select Degree Program")
    }
    val selectedProgramDegreeIndex = remember {
        mutableIntStateOf(-1)
    }
    val isProgramDegreeDropDownExpanded = remember {
        mutableStateOf(false)
    }
    val isProgramDegreeDropDownFocused = remember {
        mutableStateOf(false)
    }

    var currentPage by remember { mutableIntStateOf(1) }
    val pageTotal = 3
    val pagePadding: Dp = 8.dp
    val actualContentTopPadding: Dp = 24.dp - pagePadding

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                LocalStuddyColors.current.primary700,
                shape = RoundedCornerShape(topStart = 58.dp, topEnd = 58.dp)
            )
            .padding(bottom = 24.dp)
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
                    onSignUpClick()
                    //GOTO SIGN UP SCREEN
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
            1 -> PersonalDetailsStage(
                firstName = firstName,
                lastName = lastName,
                gender = gender,
                selectedGender = selectedGender,
                selectedGenderIndex = selectedGenderIndex,
                isGenderFocused = isGenderFocused,
                isGenderExpanded = isGenderExpanded
            )

            2 -> UniversityEnrolled(
                universityList = universityList,
                selectedUniversityItem = selectedUniversityItem,
                selectedUniversityIndex = selectedUniversityIndex,
                isUniversityDropDownExpanded = isUniversityDropDownExpanded,
                isUniversityDropDownFocused = isUniversityDropDownFocused,
                degreeProgramList = degreeProgramList,
                selectedProgramDegree = selectedProgramDegree,
                selectedProgramDegreeIndex = selectedProgramDegreeIndex,
                isProgramDegreeDropDownFocused = isProgramDegreeDropDownFocused,
                isProgramDegreeDropDownExpanded = isProgramDegreeDropDownExpanded
            )

            3 -> UploadRegistrationForm()
        }


        StuddyButtonWhite(content = if (currentPage < pageTotal) "Next" else "Confirm", onClick = {
            if (currentPage < pageTotal) {
                currentPage++
            } else {
                onConfirmClick()
                //GOTO SIGN UP SCREEN
            }
        })
    }
}

@Composable
//Contains F Name, L Name, Gender, and Birthdate
fun PersonalDetailsStage(
    firstName: MutableState<String>,
    lastName: MutableState<String>,
    gender: List<String>,
    selectedGender: MutableState<String>,
    selectedGenderIndex: MutableState<Int>,
    isGenderFocused: MutableState<Boolean>,
    isGenderExpanded: MutableState<Boolean>
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
    }

    StuddyTextFieldWhite(
        value = firstName.value,
        onValueChange = { firstName.value = it },
        label = "First Name",
        placeholder = "John"
    )
    StuddyTextFieldWhite(
        value = lastName.value,
        onValueChange = { lastName.value = it },
        label = "Last Name",
        placeholder = "Appleseed"
    )
    StuddyDropDownMenu(
        list = gender,
        selectedItem = selectedGender,
        selectedIndex = selectedGenderIndex,
        isFocused = isGenderFocused,
        isExpanded = isGenderExpanded,
        label = "Gender"
    )
    StuddyButtonWhite(enabled = false, content = "Choose Birthday Date", onClick = { /*TODO*/ })
}

@Composable
//Contains School
fun UniversityEnrolled(
    universityList: List<String>,
    selectedUniversityItem: MutableState<String>,
    selectedUniversityIndex: MutableState<Int>,
    isUniversityDropDownExpanded: MutableState<Boolean>,
    isUniversityDropDownFocused: MutableState<Boolean>,
    degreeProgramList: List<String>,
    selectedProgramDegree: MutableState<String>,
    selectedProgramDegreeIndex: MutableState<Int>,
    isProgramDegreeDropDownFocused: MutableState<Boolean>,
    isProgramDegreeDropDownExpanded: MutableState<Boolean>,
) {

    StuddyDropDownMenu(
        list = universityList,
        selectedItem = selectedUniversityItem,
        selectedIndex = selectedUniversityIndex,
        isFocused = isUniversityDropDownFocused,
        isExpanded = isUniversityDropDownExpanded,
        label = "School"
    )

    StuddyDropDownMenu(
        list = degreeProgramList,
        selectedItem = selectedProgramDegree,
        selectedIndex = selectedProgramDegreeIndex,
        isFocused = isProgramDegreeDropDownFocused,
        isExpanded = isProgramDegreeDropDownExpanded,
        label = "Degree Program"
    )
}

@Composable
//Contains Registration form upload
fun UploadRegistrationForm() {

}