package com.example.composelogin.data

import com.example.composelogin.model.PreferredStudyTime
import com.example.composelogin.ui.states.SelectionState

val preferredStudyTimeList: List<SelectionState> = listOf(
    SelectionState(
        selection = PreferredStudyTime(
            id = 1,
            name = "Early Morning"
        )
    ),

    SelectionState(
        selection = PreferredStudyTime(
            id = 2,
            name = "Morning"
        )
    ),

    SelectionState(
        selection = PreferredStudyTime(
            id = 3,
            name = "Afternoon"
        )
    ),

    SelectionState(
        selection = PreferredStudyTime(
            id = 4,
            name = "Evening"
        )
    ),

    SelectionState(
        selection = PreferredStudyTime(
            id = 5,
            name = "Late Night"
        )
    ),
)