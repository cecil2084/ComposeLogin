package com.example.composelogin.ui.screens.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.composelogin.R
import com.example.composelogin.ui.screens.styles.dimensions.StuddyDimensions
import com.example.composelogin.ui.theme.LocalStuddyColors

@Composable
fun StuddyBottomBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        /*
        * HARD CODED PA, AAYUSIN PA
        * */

        StuddyBottomBarIcons(onClick = {}) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.toga_bar),
                contentDescription = "My Vector Icon",
                modifier = Modifier.size(StuddyDimensions.iconSmall),
                tint = LocalStuddyColors.current.primary500
            )
        }

        StuddyBottomBarIcons(onClick = {}) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.messages_bar),
                contentDescription = "My Vector Icon",
                modifier = Modifier.size(StuddyDimensions.iconSmall),
                tint = LocalStuddyColors.current.primary500
            )
        }

        StuddyBottomBarIcons(onClick = {}) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.swipe_profiles_bar),
                contentDescription = "My Vector Icon",
                modifier = Modifier.size(StuddyDimensions.iconSmall),
                tint = LocalStuddyColors.current.primary500
            )
        }

        StuddyBottomBarIcons(onClick = {}) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.book),
                contentDescription = "My Vector Icon",
                modifier = Modifier.size(StuddyDimensions.iconSmall),
                tint = LocalStuddyColors.current.primary500
            )
        }

        StuddyBottomBarIcons(onClick = {}) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.weekly_challenge_bar),
                contentDescription = "My Vector Icon",
                modifier = Modifier.size(StuddyDimensions.iconSmall),
                tint = LocalStuddyColors.current.primary500
            )
        }

        StuddyBottomBarIcons(onClick = {}) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.people_bar),
                contentDescription = "My Vector Icon",
                modifier = Modifier.size(StuddyDimensions.iconSmall),
                tint = LocalStuddyColors.current.primary500
            )
        }
    }
}

@Composable
fun StuddyBottomBarIcons(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(StuddyDimensions.iconPadding),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        content()
    }
}