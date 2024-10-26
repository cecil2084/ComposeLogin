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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelogin.R
import com.example.composelogin.data.skillsList
import com.example.composelogin.model.Skill
import com.example.composelogin.model.SkillListState
import com.example.composelogin.ui.screens.styles.buttons.StuddyButtonWhite
import com.example.composelogin.ui.screens.styles.dimensions.StuddyDimensions
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.theme.StuddyTypography
import com.example.composelogin.ui.theme.fredokaFamily

@Composable
fun StrengthsScreen(
    skillListState: SkillListState,
    skillSet: List<Skill>,
    modifier: Modifier = Modifier,
    onSkillSelect: (Skill) -> Unit,
    onSkillRemove: (Skill) -> Unit,
    onConfirmClick: () -> Unit,
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

            StrengthsListInterface(
                skillListState = skillListState,
                strengthsList = skillSet,
                onSkillSelect = onSkillSelect,
                onSkillRemove = onSkillRemove
            )

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
    skillListState: SkillListState,
    modifier: Modifier = Modifier,
    strengthsList: List<Skill>,
    onSkillSelect: (Skill) -> Unit,
    onSkillRemove: (Skill) -> Unit

) {
    Column(
        modifier = modifier
            .fillMaxWidth()
//            .heightIn(min = 300.dp)
            .border(
                BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(StuddyDimensions.borderRadiusSmall)
            )
            .clip(shape = RoundedCornerShape(StuddyDimensions.borderRadiusSmall))
    ) {
        FlowRow(
            modifier = Modifier.padding(StuddyDimensions.skillListInterfacePadding),
            horizontalArrangement = Arrangement.spacedBy(StuddyDimensions.pillsSpacing),
            verticalArrangement = Arrangement.spacedBy(StuddyDimensions.pillsSpacing)
        ) {
            strengthsList.forEach {
                SkillPillRemovable(skill = it, onSkillRemove = onSkillRemove)
            }
        }

        SuggestionTextField(skillListState = skillListState, onSkillSelect = onSkillSelect)
    }
}

@Composable
fun SkillPillRemovable(
    modifier: Modifier = Modifier,
    skill: Skill,
    onSkillRemove: (Skill) -> Unit
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
            modifier = Modifier.size(12.dp),
            onClick = { onSkillRemove(skill) }
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
    skillListState: SkillListState,
    onSkillSelect: (Skill) -> Unit
) {
    val possibleInputs = when(skillListState) {
        is SkillListState.Loading -> listOf<Skill>(Skill(1, "LOADING"))
        is SkillListState.Success -> skillListState.skillList
        is SkillListState.Error -> listOf<Skill>(Skill(1, "ERROR"))
    }

    var text by remember { mutableStateOf("") }
    val suggestions = remember(text) {
        possibleInputs.filter { it.name.contains(text, ignoreCase = true) }
    }

    Column {
        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text("Type something...") }
        )

        if (suggestions.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp)
                    .background(Color.White)
            ) {
                items(suggestions) { suggestion ->
                    SuggestionItem(suggestion.name) {
                        onSkillSelect(suggestion)
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
            .clickable { onClick() }
            .padding(8.dp)
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
