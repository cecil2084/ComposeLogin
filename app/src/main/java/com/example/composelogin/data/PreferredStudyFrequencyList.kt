package com.example.composelogin.data

import com.example.composelogin.model.PreferredStudyTime
import com.example.composelogin.ui.states.SelectionState

val preferredStudyFrequencyList: List<SelectionState> = listOf(
    SelectionState(
        selection = PreferredStudyTime(
            id = 1,
            name = "Daily"
        )
    ),

    SelectionState(
        selection = PreferredStudyTime(
            id = 2,
            name = "Several times a week"
        )
    ),

    SelectionState(
        selection = PreferredStudyTime(
            id = 3,
            name = "Few times a week"
        )
    ),

    SelectionState(
        selection = PreferredStudyTime(
            id = 4,
            name = "Once a week"
        )
    ),

    SelectionState(
        selection = PreferredStudyTime(
            id = 5,
            name = "Few times a month"
        )
    ),

    SelectionState(
        selection = PreferredStudyTime(
            id = 6,
            name = "I don’t study at all"
        )
    )
)