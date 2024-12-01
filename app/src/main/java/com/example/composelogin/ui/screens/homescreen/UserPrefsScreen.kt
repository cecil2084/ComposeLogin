package com.example.composelogin.ui.screens.homescreen

import android.telephony.SignalStrength
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.composelogin.api.Token
import com.example.composelogin.model.Skill
import com.example.composelogin.model.SkillDataResponseModel
import com.example.composelogin.model.UserProfileDataResponseModel
import com.example.composelogin.ui.screens.styles.buttons.StuddyButtonBlue
import com.example.composelogin.ui.screens.styles.buttons.StuddyButtonWhite
import com.example.composelogin.ui.screens.styles.dimensions.StuddyDimensions
import com.example.composelogin.ui.states.UserProfileState
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.theme.StuddyTypography
import com.example.composelogin.ui.viewmodels.HomeViewModel

@Composable
fun UserPrefsScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
    onClick: () -> Unit
) {
    Column(
//        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        val uiState by viewModel.userProfileState.collectAsState()
        var university by remember { mutableStateOf("loading...") }
        var degreeProgram by remember { mutableStateOf("loading...") }

        when (uiState) {
            is UserProfileState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Text(
                        text = "Loading",
                        color = Color.LightGray,
                        style = StuddyTypography.hL
                    )
                }
            }

            is UserProfileState.Error -> Text("Error: ${(uiState as UserProfileState.Error).message}")
            is UserProfileState.Success -> {
                val userProfile: UserProfileDataResponseModel =
                    (uiState as UserProfileState.Success).userProfile

                LaunchedEffect(true) {
                    university = viewModel.getUniversity(userProfile.university_id ?: "null")
                    degreeProgram =
                        viewModel.getDegreeProgram(userProfile.degree_program_id ?: "null")
                }

                UserProfile(
                    name = userProfile.given_name + " " + userProfile.family_name,
                    profilePicture = userProfile.display_photo_url,
                    university = university,
                    degreeProgram = degreeProgram,
                    about = userProfile.about ?: "",
                    strengths = userProfile.user_strengths ?: listOf(),
                    weaknesses = userProfile.user_weaknesses ?: listOf()
                )
            }
        }

//        Button(onClick = onClick) {
//            Token.token = null
//            Text("Log out")
//        }

        Spacer(modifier = Modifier.height(32.dp))

        StuddyButtonBlue(
            content = "Log out",
            onClick = {
                onClick()
                Token.token = null
            }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UserProfile(
    name: String,
    profilePicture: String?,
    university: String,
    degreeProgram: String,
    about: String,
    strengths: List<SkillDataResponseModel>,
    weaknesses: List<SkillDataResponseModel>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Profile",
            color = LocalStuddyColors.current.primary700,
            style = StuddyTypography.hL
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = CircleShape)
                    .border(
                        border = BorderStroke(5.dp, LocalStuddyColors.current.primary700),
                        shape = CircleShape
                    )
            ) {
                if (profilePicture != null)
                    AsyncImage(
                        model = profilePicture,
                        contentDescription = "User profile picture"
                    )
            }

            Spacer(Modifier.width(16.dp))

            Column() {
                Text(
                    text = name,
                    color = LocalStuddyColors.current.primary700,
                    style = StuddyTypography.h4
                )
                Text(
                    text = university,
                    color = LocalStuddyColors.current.lightNeutral500,
                    style = StuddyTypography.pSM
                )
                Text(
                    text = degreeProgram,
                    color = LocalStuddyColors.current.accent2700,
                    style = StuddyTypography.pXXS
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // ABOUT PAGE
        Text(
            text = "About",
            color = LocalStuddyColors.current.lightNeutral500,
            style = StuddyTypography.h4
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = about,
            color = LocalStuddyColors.current.lightNeutral500,
            style = StuddyTypography.pXS
        )

        Spacer(modifier = Modifier.height(32.dp))

        // STRENGTHS
        Text(
            text = "Strengths",
            color = LocalStuddyColors.current.lightNeutral500,
            style = StuddyTypography.h4
        )

        Spacer(modifier = Modifier.height(16.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(StuddyDimensions.pillsSpacing),
            verticalArrangement = Arrangement.spacedBy(StuddyDimensions.pillsSpacing)
        ) {
            strengths.forEach {
                ItemSkillPill(it.name)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // WEAKNESSES
        Text(
            text = "Weaknesses",
            color = LocalStuddyColors.current.lightNeutral500,
            style = StuddyTypography.h4
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (weaknesses.isNotEmpty())
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(StuddyDimensions.pillsSpacing),
                verticalArrangement = Arrangement.spacedBy(StuddyDimensions.pillsSpacing)
            ) {
                weaknesses.forEach {
                    ItemSkillPillAccent(it.name)
                }
            }
        else
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Nothing to Show",
                    color = Color.LightGray,
                    style = StuddyTypography.hL
                )
            }
    }
}