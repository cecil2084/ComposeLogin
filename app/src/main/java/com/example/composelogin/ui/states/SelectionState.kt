package com.example.composelogin.ui.states

import com.example.composelogin.model.SelectionInterface

data class SelectionState(
    val selection: SelectionInterface,
    val isSelected: Boolean = false
)