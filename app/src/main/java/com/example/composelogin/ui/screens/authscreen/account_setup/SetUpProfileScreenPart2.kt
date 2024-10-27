package com.example.composelogin.ui.screens.authscreen.account_setup

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composelogin.R
import com.example.composelogin.SetUpNavRoutes
import com.example.composelogin.model.Skill
import com.example.composelogin.model.TOTAL_PAGE
import com.example.composelogin.ui.screens.styles.buttons.StuddyButtonWhite
import com.example.composelogin.ui.screens.styles.dimensions.StuddyDimensions
import com.example.composelogin.ui.states.SkillListState
import com.example.composelogin.ui.theme.LocalStuddyColors
import com.example.composelogin.ui.theme.StuddyTypography
import com.example.composelogin.ui.viewmodels.SetUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SetUpProfileScreenPart2(
    modifier: Modifier = Modifier,
    viewModel: SetUpViewModel = viewModel(),
    onCancelLastClick: () -> Unit,
    onConfirmLastClick: () -> Unit,
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQueryState by viewModel.searchQueryState.collectAsState()

    var showStrengthBottomSheet by remember { mutableStateOf(false) }
    val strengthSheetState = rememberModalBottomSheetState()

    var showWeaknessBottomSheet by remember { mutableStateOf(false) }
    val weaknessSheetState = rememberModalBottomSheetState()

    Scaffold(
        modifier = modifier
            .background(LocalStuddyColors.current.primary700)
            .statusBarsPadding(),
        topBar = {
            ProgressBar(
                modifier = Modifier
                    .background(LocalStuddyColors.current.primary700)
                    .zIndex(9f),
                progressRatio = uiState.currentPage / TOTAL_PAGE.toFloat(),
                onBackClick = {
                    if (uiState.currentPage != 1) {
                        navController.popBackStack()
                    } else {
                        onCancelLastClick()
                    }
                }
            )
        },
        bottomBar = {
            StuddyButtonWhite(
                stringResource(R.string.Confirm),
                modifier = Modifier
                    .background(LocalStuddyColors.current.primary900.copy(alpha = 0.9f))
                    .fillMaxWidth()
                    .padding(20.dp)
                    .navigationBarsPadding(),
                onClick = {
                    when (uiState.currentPage) {
                        1 -> navController.navigate(SetUpNavRoutes.WEAKNESSES)
                        2 -> navController.navigate(SetUpNavRoutes.PREFERRED_STUDY_TIME)
                        3 -> navController.navigate(SetUpNavRoutes.PREFERRED_FREQUENCY)
                        4 -> navController.navigate(SetUpNavRoutes.PREFERRED_TRAITS)
                        5 -> onConfirmLastClick()
                    }
                },
            )
        }
    ) { innerPadding ->

        NavHost(
            modifier = Modifier.background(LocalStuddyColors.current.primary700),
            navController = navController,
            startDestination = SetUpNavRoutes.STRENGTHS,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
        ) {
            composable(
                route = SetUpNavRoutes.STRENGTHS,
                enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
                popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
                popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
            ) {

                viewModel.updatePage(1)

                SkillsScreen(
                    title = R.string.strengths,
                    description = R.string.strengths_info,
                    skillSet = uiState.strengths,
                    modifier = Modifier.padding(
                        start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                        top = innerPadding.calculateTopPadding(),
                        end = innerPadding.calculateEndPadding(LayoutDirection.Ltr)
                    ),
                    onBrowseSkillClick = {
                        showStrengthBottomSheet = !showStrengthBottomSheet
                    },
                    onSkillRemove = { viewModel.removeStrengthSkill(it) }
                )

                if (showStrengthBottomSheet) {
                    ModalBottomSheet(
                        shape = RectangleShape,
                        containerColor = Color.White,
                        modifier = Modifier
                            .fillMaxSize(),
                        onDismissRequest = {
                            showStrengthBottomSheet = false
                        },
                        sheetState = strengthSheetState
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        ) {
                            ModalSkillsListInterface(
                                searchQuery = searchQueryState,
                                skillListState = viewModel.skillListState,
                                skillsList = uiState.strengths,
                                onSearchChange = {
                                    viewModel.onSearchQueryChanged(it)
                                },
                                onSkillSelect = { viewModel.addStrengthSkill(it) },
                                onSkillRemove = { viewModel.removeStrengthSkill(it) },
                                onSkillSuggest = { viewModel.filterSuggestion(it) }
                            )
                        }
                    }
                }
            }

            composable(
                route = SetUpNavRoutes.WEAKNESSES,
                enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
                popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
                popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
            ) {
                viewModel.updatePage(2)

                SkillsScreen(
                    title = R.string.Weaknesses,
                    description = R.string.weaknesses_info,
                    skillSet = uiState.weaknesses,
                    modifier = Modifier.padding(
                        start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                        top = innerPadding.calculateTopPadding(),
                        end = innerPadding.calculateEndPadding(LayoutDirection.Ltr)
                    ),
                    onBrowseSkillClick = {
                        showWeaknessBottomSheet = !showWeaknessBottomSheet
                    },
                    onSkillRemove = { viewModel.removeWeaknessSkill(it) }
                )

                if (showWeaknessBottomSheet) {
                    ModalBottomSheet(
                        shape = RectangleShape,
                        containerColor = Color.White,
                        modifier = Modifier.fillMaxSize(),
                        onDismissRequest = {
                            showWeaknessBottomSheet = false
                        },
                        sheetState = weaknessSheetState
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        ) {
                            ModalSkillsListInterface(
                                searchQuery = searchQueryState,
                                skillListState = viewModel.skillListState,
                                skillsList = uiState.weaknesses,
                                onSearchChange = {
                                    viewModel.onSearchQueryChanged(it)
                                },
                                onSkillSelect = { viewModel.addWeaknessSkill(it) },
                                onSkillRemove = { viewModel.removeWeaknessSkill(it) },
                                onSkillSuggest = {
                                    viewModel.filterSuggestion(it)
                                }
                            )
                        }
                    }
                }
            }

            composable(
                route = SetUpNavRoutes.PREFERRED_STUDY_TIME,
                enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
                popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
                popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
            ) {
                viewModel.updatePage(3)

                PreferredStudyTimeScreen(
                    choiceList = uiState.preferredStudyTime,
                    title = R.string.preferred_study_time,
                    description = R.string.preferred_traits_description,
                    modifier = Modifier.padding(innerPadding),
                    onSelectionChange = {
                        if (uiState.preferredStudyTime.count { item -> item.isSelected } < 3 || it.isSelected)
                            viewModel.toggleStudyTime(it)
                    }
                )
            }

            composable(
                route = SetUpNavRoutes.PREFERRED_FREQUENCY,
                enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
                popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
                popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
            ) {
                viewModel.updatePage(4)

                PreferredStudyTimeScreen(
                    choiceList = uiState.preferredStudyFrequency,
                    title = R.string.preferred_study_frequency,
                    description = R.string.preferred_study_frequency_description,
                    modifier = Modifier.padding(innerPadding),
                    onSelectionChange = {
                        if (uiState.preferredStudyFrequency.count { item -> item.isSelected } < 3 || it.isSelected)
                            viewModel.toggleStudyFrequency(it)
                    }
                )
            }

            composable(
                route = SetUpNavRoutes.PREFERRED_TRAITS,
                enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
                popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
                popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
            ) {
                viewModel.updatePage(5)

                PreferredStudyTimeScreen(
                    choiceList = uiState.studyPartnerTraits,
                    title = R.string.preferred_traits,
                    description = R.string.preferred_traits_description,
                    modifier = Modifier.padding(innerPadding),
                    onSelectionChange = {
                        if (uiState.studyPartnerTraits.count { item -> item.isSelected } < 3 || it.isSelected)
                            viewModel.toggleStudyTraits(it)
                    }
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun ModalSkillsListInterface(
    searchQuery: String,
    skillListState: SkillListState,
    modifier: Modifier = Modifier,
    skillsList: List<Skill>,
    onSearchChange: (String) -> Unit,
    onSkillSelect: (Skill) -> Unit,
    onSkillRemove: (Skill) -> Unit,
    onSkillSuggest: (String) -> List<Skill>
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .border(
                    BorderStroke(1.dp, LocalStuddyColors.current.primary900),
                    shape = RoundedCornerShape(StuddyDimensions.borderRadiusSmall)
                )
                .clip(shape = RoundedCornerShape(StuddyDimensions.borderRadiusSmall)),
        ) {
            FlowRow(
                modifier = Modifier
                    .height(120.dp)
                    .padding(StuddyDimensions.skillListInterfacePadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(StuddyDimensions.pillsSpacing),
                horizontalArrangement = Arrangement.spacedBy(StuddyDimensions.pillsSpacing)
            ) {
                skillsList.forEach {
                    SkillPillRemovableBlue(skill = it, onSkillRemove = onSkillRemove)
                }
            }

            HorizontalDivider(
                color = LocalStuddyColors.current.primary900,
                thickness = 1.dp
            )

            BasicTextField(
                value = searchQuery,
                onValueChange = onSearchChange,
                textStyle = StuddyTypography.pXS,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
                    },
                decorationBox = { innerTextField ->
                    if (searchQuery.isEmpty()) {
                        Text(
                            text = "Search Skills",
                            style = StuddyTypography.pXS,
                            color = LocalStuddyColors.current.primary900
                        )
                    }
                    innerTextField()
                },
                singleLine = true,
            )
        }
        when (skillListState) {
            is SkillListState.Success -> LazySkillsColumn(
                onSkillSelect = onSkillSelect,
                skillList = onSkillSuggest(searchQuery)
            )

            is SkillListState.Loading -> Text("LOADING...")
            is SkillListState.Error -> Text("ERROR LOADING SKILLS :(")
        }
    }
}

@Composable
fun LazySkillsColumn(
    onSkillSelect: (Skill) -> Unit,
    skillList: List<Skill>
) {
    Box(
        modifier = Modifier.padding(top = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.White, Color.Transparent)
                    )
                )
                .zIndex(10f)
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(skillList) { skill ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSkillSelect(skill)
                        }
                        .padding(vertical = 15.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.book),
                        contentDescription = "logo"
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(
                        text = skill.name,
                        style = StuddyTypography.pXS,
                        color = LocalStuddyColors.current.primary900
                    )
                }
            }
        }
    }
}

@Composable
fun SkillPillRemovableBlue(
    modifier: Modifier = Modifier,
    skill: Skill,
    onSkillRemove: (Skill) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .border(
                BorderStroke(1.dp, LocalStuddyColors.current.primary700),
                shape = RoundedCornerShape(StuddyDimensions.buttonBorderRadius)
            )
            .padding(
                vertical = StuddyDimensions.pillTextPadding,
                horizontal = StuddyDimensions.pillTextPadding
            )
    ) {
        Text(
            text = skill.name,
            color = LocalStuddyColors.current.primary700,
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
                tint = LocalStuddyColors.current.primary700
            )
        }
    }
}

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    progressRatio: Float = 0f,
    onBackClick: () -> Unit
) {
    val ratio by animateFloatAsState(
        targetValue = progressRatio,
//        animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy, // Adjusts the bounciness
            stiffness = Spring.StiffnessLow // Adjusts the resistance, making it more or less elastic
        ),
        label = "ratio animation"
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(modifier = Modifier.padding(start = 8.dp), onClick = onBackClick) {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.back_button),
                contentDescription = "back button",
                tint = Color.White
            )
        }
        Box(
            modifier = Modifier
                .padding(end = 25.dp)
                .clip(RoundedCornerShape(100.dp))
                .fillMaxWidth()
                .background(LocalStuddyColors.current.darkNeutral600)
                .aspectRatio(15f)
        ) {
            val progressColor: Color = LocalStuddyColors.current.accent2700
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRoundRect(
                    color = progressColor,
                    size = Size(this.size.width * ratio, this.size.height),
                    cornerRadius = CornerRadius(this.size.height)
                )
            }
        }
    }
}