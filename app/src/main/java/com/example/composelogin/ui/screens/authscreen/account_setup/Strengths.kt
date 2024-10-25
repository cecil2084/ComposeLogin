package com.example.composelogin.ui.screens.authscreen.account_setup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.R
import com.example.composelogin.model.Skill
import com.example.composelogin.ui.screens.styles.buttons.StuddyButtonWhite
import com.example.composelogin.ui.screens.styles.dimensions.StuddyDimensions
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.theme.StuddyTypography
import com.example.composelogin.ui.theme.fredokaFamily

@Composable
fun StrengthsScreen(
    skillSet: List<Skill>,
    modifier: Modifier = Modifier,
    onConfirmClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .background(LocalStuddyColors.current.primary700)
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            Text(
                text = stringResource(R.string.strengths),
                fontFamily = fredokaFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontSize = 20.sp,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.strengths_info),
                style = StuddyTypography.pXS,
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(20.dp))

//            StrengthsListInterface(strengthsList = skillSet)
            StrengthsListInterface(strengthsList = listOf(
                Skill(1, "Public Speaking"),
                Skill(2, "Time Management"),
                Skill(3, "Problem Solving"),
                Skill(4, "Project Management"),
                Skill(5, "Teamwork"),
                Skill(6, "Creativity"),
                Skill(7, "Emotional Intelligence"),
                Skill(8, "Adaptability"),
                Skill(9, "Critical Thinking"),
                Skill(10, "Leadership"),
                Skill(11, "Customer Service"),
                Skill(12, "Negotiation")
            ))

        }
        StuddyButtonWhite(
            stringResource(R.string.Confirm),
            onClick = onConfirmClick,
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StrengthsListInterface(
    modifier: Modifier = Modifier,
    strengthsList: List<Skill>
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
//            .heightIn(min = 300.dp)
            .border(
                BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(StuddyDimensions.borderRadiusSmall)
            )
    ) {
        FlowRow(
            modifier = Modifier.padding(StuddyDimensions.skillListInterfacePadding),
            horizontalArrangement = Arrangement.spacedBy(StuddyDimensions.pillsSpacing),
            verticalArrangement = Arrangement.spacedBy(StuddyDimensions.pillsSpacing)
        ) {
            strengthsList.forEach {
                skillPillRemovable(skill = it, onSkillRemove = {})
            }
        }
    }
}

@Composable
fun skillPillRemovable(
    modifier: Modifier = Modifier,
    skill: Skill,
    onSkillRemove: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .border(
                BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(StuddyDimensions.buttonBorderRadius)
            )
            .padding(
                vertical = StuddyDimensions.pillTextPadding,
                horizontal = StuddyDimensions.pillTextPadding
            )
    ) {
        Text(
            text = skill.name,
            color = Color.White,
            style = StuddyTypography.pXSSmaller
        )

        Spacer(modifier = Modifier.width(5.dp))

        IconButton(
            modifier= Modifier.size(12.dp),
            onClick = onSkillRemove
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.x_circle),
                contentDescription = "remove ${skill.name}",
                tint = Color.White
            )
        }
    }
}


@Composable
fun SuggestionTextField(
    possibleInputs: List<String> // List of possible suggestions
) {
    // State to hold the current text input and suggestions
    var text by remember { mutableStateOf("") }
    val suggestions = remember(text) {
        // Filter the suggestions based on the current input
        possibleInputs.filter { it.contains(text, ignoreCase = true) }
    }

    Column {
        // TextField for user input
        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText // Update the text input state
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Type something...") }
        )

        // Show suggestions if there are any
        if (suggestions.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.White) // Background for suggestions
            ) {
                items(suggestions) { suggestion ->
                    // Suggestion item
                    SuggestionItem(suggestion) {
                        text = suggestion // Update text when suggestion is clicked
                    }
                }
            }
        }
    }
}

@Composable
fun SuggestionItem(suggestion: String, onClick: () -> Unit) {
    Text(
        text = suggestion,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() } // Handle item click
            .padding(8.dp) // Padding for suggestion item
    )
}


//@Composable
//fun StrengthsScreen(
//    onConfirmClick: () -> Unit,
//    onBackClick: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Column(
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier
//            .fillMaxSize()
//            .background(LocalStuddyColors.current.primary700)
//            .statusBarsPadding()
//            .navigationBarsPadding()
//    ) {
//        ProgressBar(progressRatio = 0.4f)
//
//        MockDisplay(modifier.weight(1f))
//
//        StuddyButtonWhite(
//            stringResource(R.string.Confirm),
//            onClick = {},
//        )
//    }
//}
