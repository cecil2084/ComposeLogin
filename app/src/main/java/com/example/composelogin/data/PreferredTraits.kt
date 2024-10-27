package com.example.composelogin.data

import com.example.composelogin.model.PreferredStudyTime
import com.example.composelogin.ui.states.SelectionState

val preferredTraitsList: List<SelectionState> = listOf(
    SelectionState(
        selection = PreferredStudyTime(
            id = 1,
            name = "Same field of study"
        )
    ),

    SelectionState(
        selection = PreferredStudyTime(
            id = 2,
            name = "Same schedule"
        )
    ),

    SelectionState(
        selection = PreferredStudyTime(
            id = 3,
            name = "Same university"
        )
    ),

    SelectionState(
        selection = PreferredStudyTime(
            id = 4,
            name = "With similar skills"
        )
    ),

    SelectionState(
        selection = PreferredStudyTime(
            id = 5,
            name = "With complementary skills"
        )
    )
)